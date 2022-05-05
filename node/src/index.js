import Application from './Core/Application';
import RoutesBuilder from './Core/RoutesBuilder';

import Configuration from './Configuration';
import * as controllers from './Controller';

import Logger from './Middleware/Logger';

new Application(async (app) => {
  new Logger(app);

  new RoutesBuilder(app, controllers);
  console.log();
}, Configuration.getPortOfApp());