let pageCount = 0;
function consumeListApi(page,country,state) {

    let request = new XMLHttpRequest()
    let url = "http://127.0.0.1:8080/api/v1/customer";

    if (!country && state){
        url = url + "/state/"+state;
    }else if (country && !state){
        url = url + "/country/"+country;
    }else if (country && state) {
        url = url + "/country/"+country+"/state/"+state;
    }

     url=url+'?page='+page;


    request.open("GET",url);

    request.send();
    request.onload = () => {
        if(request.status === 200){
            let numbers = JSON.parse(request.responseText);
            //console.log(numbers.totalNumber);

            let customers = numbers.customerList;

            let dataDiv = '';


            let count = 10*page+1;
            for (let x in customers) {


                            dataDiv = dataDiv+ `<tr>\n 
                              <th scope=\"row\">${count}</th>\n
                               <td>${customers[x].name}</td>\n 
                               <td>${customers[x].phone}</td>\n
                                  <td>${customers[x].country.countryName}</td>\n
                               <td>${customers[x].country.state}</td>\n
                         </tr>`


                count++;
            }

            console.log(dataDiv);

            document.getElementById('data').innerHTML=dataDiv;


            let pagesNum = numbers.totalNumber;

            if (pageCount == 0)
                document.getElementById("prev").style.display='none';

            if (pageCount>0)
                document.getElementById("prev").style.display='block';


            if (pageCount<pagesNum-1)
                document.getElementById("next").style.display='block';

            if (pageCount>=pagesNum-1)
                document.getElementById("next").style.display='none';



            // console.log(JSON.parse(request.response))
        } else {
            console.log("Page not found")// if link is broken, output will be page not found
        }




    }
}

window.onload = function() {
    consumeListApi(0);
};




/*   for (var a : list){
        dataDiv = "  <tr>\n" +
            "                <th scope=\"row\">1</th>\n" +
            "                <td>a.</td>\n" +
            "                <td>Otto</td>\n" +
            "                <td>@mdo</td>\n" +
            "              </tr>"


        }

      */





[]