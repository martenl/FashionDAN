

var Api = {};

Api.graphqlCall = function(data, success, error) {
  var query = "";
  var data = {query: query};
  axios.post("/graphql",data);
};
Api.saveCampaign = function() {

};

Api.deleteCampaign = function(id) {

};

Api.updateCampaign = function() {

};

Api.loadCampaign = function(id) {

};