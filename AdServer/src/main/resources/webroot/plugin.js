(function(document, window, undefined) {

  var createAd = function(json) {

    var link = document.createElement("a");
    link.setAttribute("href", json.url);

    var creative = document.createElement("img");
    creative.setAttribute("src", json.creative);
    link.appendChild(creative);

    var callToAction = document.createElement("h3");
    callToAction.innerHtml = json.cta;
    link.appendChild(callToAction);

    var adPlacing = document.getElementById("fashionDAN-ad");
    adPlacing.innerHtml = "";
    adPlacing.appendChild(link);
  };

  document.addEventListener('DOMContentLoaded', (event) => {
      //if(document.fashionDan != undefined) {
          fetch('http://localhost:8080/ad/123')
            .then(response => response.json())
            .then(data => createAd(data));
      //}
  });

})(document, window);