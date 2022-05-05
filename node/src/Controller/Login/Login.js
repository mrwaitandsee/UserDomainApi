import BaseController from '../../Core/BaseController';
import Configuration from '../../Configuration/Configuration';
import jwt from 'jsonwebtoken';
import bcrypt from 'bcrypt';

const method = 'POST';
const action = 'user-management/login';
export class Login extends BaseController {
  constructor(router) {
    super(router, method, action);
    super.initialization(this.handler);
  }

  async handler(request, response, next) {
    const { name, password } = request.body;
    if (name && password) {
      const client = await Configuration.getDBClient();
      const { rows, rowCount } = await client.query('SELECT * FROM "user" WHERE name = $1', [name]);
      client.release();
      if (rowCount == 0) {
        super.res(response, 400, false, 'Bad request.');
        return;
      } else {
        const { id } = rows[0];
        if (name === rows[0].name) {
          const result = await bcrypt.compare(password, rows[0].password);
          if (result) {
            const accessToken = jwt.sign({
              user: id,
            }, Configuration.getJwtSecret());
            super.res(response, 200, true, {
              accessToken,
            });
          } else {
            super.res(response, 400, false, 'Wrong data.');
          }
        } else {
          super.res(response, 400, false, 'Wrong data.');
        }
      }
    } else {
      super.res(response, 400, false, 'Bad request.');
    }
  }
}
