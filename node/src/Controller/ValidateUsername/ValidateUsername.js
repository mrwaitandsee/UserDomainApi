import BaseController from '../../Core/BaseController';
import { ValidateUsernameService } from "../../Service/ValidateUsernameService/ValidateUsernameService";

const method = 'POST';
const action = 'user-management/validate-username';
export class ValidateUsername extends BaseController {
  constructor(router) {
    super(router, method, action);
    super.initialization(this.handler);
  }

  async handler(request, response, next) {
    const { name } = request.body;
    if (name) {
      const result = await new ValidateUsernameService().validate(name);
      super.res(response, 200, true, result);
    } else {
      super.res(response, 400, false, 'Bad request.');
    }
  }
}
