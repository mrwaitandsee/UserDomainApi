import Configuration from '../../Configuration/Configuration';

export class ValidateUsernameService {
  async validate(name) {
    const client = await Configuration.getDBClient();
    const { rows } = await client.query('SELECT * FROM "user" WHERE name = $1', [name]);
    client.release();
    if (rows.length == 0) {
      return true;
    } else {
      return false;
    }
  }
}