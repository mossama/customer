console.log("Hello");
function consumeListApi(page) {


    fetch("http://127.0.0.1:8080/api/v1/customer?page="+page)
        .then(function(response) {
            if (!response.ok) {
                throw Error(response.statusText);
            }
            return response;
        }).then(function(response) {
        console.log("ok");
    }).catch(function(error) {
        console.log(error);
    });
}


/*   for (var a : list){
        dataDiv = "  <tr>\n" +
            "                <th scope=\"row\">1</th>\n" +
            "                <td>a.</td>\n" +
            "                <td>Otto</td>\n" +
            "                <td>@mdo</td>\n" +
            "              </tr>"


        }

      */
