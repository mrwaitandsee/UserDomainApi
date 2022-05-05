import Configuration from '../../Configuration/Configuration';

export class ValidateUsernameService {
  async validate(name) {
    const client = Configuration.getDBClient();
    await client.connect();
    const { rows } = await client.query('SELECT * FROM "user" WHERE name = $1', [name]);
    await client.end();
    if (rows.length == 0) {
      return true;
    } else {
      return false;
    }
  }
}