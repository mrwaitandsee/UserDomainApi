import express from 'express';
import cors from 'cors';
import bodyParser from 'body-parser';

export default class Application {
  constructor(main, port) {
    this.app = express();

    this.app.use(bodyParser.urlencoded({ extended: false }));
    this.app.use(bodyParser.json());
    this.app.use(cors());

    console.log('Welcome!');

    main(this.app);

    this.app.listen(port, '0.0.0.0', function () {
      console.log('Listening on *:' + port);
      console.log();
    });
  }
}