import BaseController from '../../Core/BaseController';
import Configuration from '../../Configuration/Configuration';
import { transaction } from '../../Core/Transaction';
import { ValidateUsernameService } from "../../Service/ValidateUsernameService/ValidateUsernameService";
import bcrypt from 'bcrypt';
import crypto from 'crypto';

const method = 'POST';
const action = 'user-management/registration';
export class Registration extends BaseController {
  constructor(router) {
    super(router, method, action);
    super.initialization(this.handler);
  }

  async handler(request, response, next) {
    const { name, password, project } = request.body;

    if (!name) {
      super.res(response, 400, false, 'Bad request.');
      return;
    } else {
      const result = await new ValidateUsernameService().validate(name);
      if (!result) {
        super.res(response, 400, false, 'Bad request. User already exists.');
        return;
      }
    }

    if (name && password && project) {
      if (project.length == 0) {
        super.res(response, 400, false, 'Bad request.');
        return;
      }

      const dbClient = await Configuration.getDBClient();
      const projects = await dbClient.query('SELECT * FROM \"project\" WHERE name IN ($1)', [project.join()])
      await dbClient.release();
      const dbClient4Transaction = await Configuration.getDBClient()
      transaction(dbClient4Transaction, async (client) => {
        const hash = await bcrypt.hash(password, Configuration.getBcryptSaltRounds());
        const newUser = {
          id: crypto.randomUUID(),
          name,
          password: hash
        };
        const role = await client.query('SELECT * FROM "role" WHERE name = \'user\'');
        await client.query('INSERT INTO "user" VALUES ($1, $2, $3)', [newUser.id, newUser.name, newUser.password]);

        const newUserRole = {
          id: crypto.randomUUID(),
          userId: newUser.id,
          roleId: role.rows[0].id
        }
        await client.query('INSERT INTO "user_role" VALUES ($1, $2, $3)', [newUserRole.id, newUserRole.userId, newUserRole.roleId]);

        const projectUserEntityes = [];
        projects.rows.forEach(project => {
          projectUserEntityes.push(`('${crypto.randomUUID()}', '${project.id}', '${newUser.id}')`);
        });
        await client.query(`INSERT INTO "project_user" VALUES ${projectUserEntityes.join()}`);

        super.res(response, 200, true, {
          id: newUser.id,
          name: newUser.name
        });
      });
    } else {
      super.res(response, 400, false, 'Bad request.');
    }
  }
}
