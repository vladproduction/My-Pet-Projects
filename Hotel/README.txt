Implemented restful Api for "Hotel".
Used h2 DataBase.
Implemented integration tests for repository and service.
Used Postman as a main tool for integration tests.
To run Postman tests please go to test/resources/postman and import collections from postman tool.
Collections have tests to check restful Api (in each request there is a section test):

pm.test("status code is 200", function(){
    pm.response.to.have.status(200);
})

pm.test("verify response body", function(){
    var json = pm.response.json();
    var id = json.id;
    pm.globals.set("createRoomId", id)
    //pm.expect(json.id).to.not(null);
    pm.expect(json.roomType).to.eql("LUX");
    pm.expect(json.capacity).to.eql(4);
    pm.expect(json.roomNumber).to.eql(1);
    pm.expect(json.price).to.eql(0);
})

To run all tests in the collection click "..." and select "run collection"