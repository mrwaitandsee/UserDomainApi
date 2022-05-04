var assert = require('assert');

function sum(a, b) {
  return a + b;
}

describe("pow", function() {
  it("складывает 2 и 3", function() {
    assert.equal(sum(2, 3), 5);
  });
});