import BaseController from '../../Core/BaseController';
import Configuration from '../../Configuration/Configuration';
import jwt from 'jsonwebtoken';

const method = 'POST';
const action = 'auth-management/authentication';
export class Authentication extends BaseController {
  constructor(router) {
    super(router, method, action);
    super.initialization(this.handler);
  }

  async handler(request, response, next) {
    const { accessToken } = request.body;
    if (accessToken) {
      try {
        const user = await jwt.verify(accessToken, Configuration.getJwtSecret());
        const client = await Configuration.getDBClient();
        const { rows } = await client.query('SELECT * FROM "user" WHERE id = $1', [user.user]);
        client.release();
        super.res(response, 200, true, {
          id: user.user,
          name: rows[0].name
        });
      } catch(err) {
        super.res(response, 400, false, 'Incorrect token.');
      }
    } else {
      super.res(response, 400, false, 'Bad request.');
    }
  }
}
