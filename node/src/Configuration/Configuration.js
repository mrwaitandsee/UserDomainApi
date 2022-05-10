import { Client, Pool } from 'pg';

export default class Configuration {
  static dbPool = null;

  static getPortOfApp() {
    return process.env.PORT || 5000;
  }

  static async getDBClient() {
    if (!this.dbPool) {
      this.dbPool = new Pool({
        host: process.env.DB_HOST || '172.20.0.2',
        port: process.env.DB_PORT || 5432,
        user: process.env.DB_USER || 'postgres',
        password: process.env.DB_PASSWORD || 'strong-password',
        database: process.env.DB_NAME || 'user-domain-api',
        max: 100,
      });
    }

    return this.dbPool.connect();
    
    // return new Client({
      // host: process.env.DB_HOST || '172.31.0.2',
      // port: process.env.DB_PORT || 5432,
      // user: process.env.DB_USER || 'postgres',
      // password: process.env.DB_PASSWORD || 'strong-password',
      // database: process.env.DB_NAME || 'user-domain-api'
    // });
  }

  static getBcryptSaltRounds() {
    return 8;
  }

  static getJwtSecret() {
    return 'strong-secret';
  }
}
