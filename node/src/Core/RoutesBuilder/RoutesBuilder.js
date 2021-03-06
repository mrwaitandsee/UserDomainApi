import express from 'express';

export default class RoutesBuilder {
  constructor(app, controllers) {
    this.router = express.Router();

    for (const value of Object.values(controllers)) {
      new value(this.router);
    }

    app.use('/api', this.router);
    
    app.get('/', (req, res) => {
      res.send('You shall not pass!')
    });
  }

  getRouter() {
    return this.router;
  }
}