<%--
  Created by IntelliJ IDEA.
  User: Marco
  Date: 5/15/15
  Time: 11:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>QCS2015</title>

  <link href='http://fonts.googleapis.com/css?family=Dancing+Script' rel='stylesheet' type='text/css'>

  <link rel="stylesheet" type="text/css" href="visual/bootstrap-3.3.4-dist/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="visual/base.css">

</head>
<body>

<div class="page-header">
  <h3><small>SQD</small> Insulin Calculator</h3>
</div>

<div class="container-fluid">
  <div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-8 col-md-header">
      <button type="button" class="btn btn-primary btn-lg" id="btn-standard">
        Standard Insulin Sensitivity
        <br>
        <small>mealtime insulin dose</small>
      </button>
      <button type="button" class="btn btn-info btn-lg" id="btn-personal">
        Personal Insulin Sensitivity
        <br>
        <small>mealtime insulin dose</small>
      </button>
      <button type="button" class="btn btn-warning btn-lg" id="btn-background">
        Background Insulin Dose
      </button>
    </div>
    <div class="col-md-2"></div>
  </div>

  <br>

  <!-- window where it is showed all the input fields for each calculation of insulin -->
  <div class="row" id="main-window">
    <div class="col-md-2"></div>
    <div class="col-md-8 col-md-body">
      <div class="row">
        <div class="col-md-8 col-md-menu">

          <div id="standard-insulin">
            <h4>Standard Insulin Sensitivity</h4>

            <!-- Total grams of carbohydrates in the meal -->
            <div class="input-group">
              <input type="number" min="60" max="120" id="std_tgcm"
                     class="form-control" placeholder="Total grams of carbohydrates in the meal"
                     aria-describedby="sizing-addon1" data-toggle="tooltip" data-placement="bottom"
                     title="60 <= value <= 120" onkeypress="return isValidNumeric(event)"
                     onchange="enableButtonStandard()" onkeyup="enableButtonStandard()" onblur="enableButtonStandard()">
              <span class="input-group-addon" id="sizing-addon1">g</span>
            </div>

            <br>

            <!-- Total grams of carbohydrates processed by 1 unit of rapid acting insulin -->
            <!-- default = 12 -->
            <div class="input-group">
              <input type="number" min="10" max="15" id="std_tgcp"
                     class="form-control" placeholder="Total grams of carbohydrates processed by 1 unit of rapid acting insulin"
                     aria-describedby="sizing-addon2" data-toggle="tooltip" data-placement="bottom"
                     title="10 <= value <= 15 (default = 12)" onkeypress="return isValidNumeric(event)"
                     onchange="enableButtonStandard()" onkeyup="enableButtonStandard()" onblur="enableButtonStandard()">
              <span class="input-group-addon" id="sizing-addon2">g</span>
            </div>

            <br>

            <!-- Actual blood sugar level measured before the meal -->
            <div class="input-group">
              <input type="number" min="120" max="250" id="std_abs" class="form-control"
                     placeholder="Actual blood sugar level measured before the meal"
                     aria-describedby="sizing-addon3" data-toggle="tooltip" data-placement="bottom"
                     title="120 <= value <= 250" onkeypress="return isValidNumeric(event)"
                     onchange="enableButtonStandard()" onkeyup="enableButtonStandard()" onblur="enableButtonStandard()">
              <span class="input-group-addon" id="sizing-addon3">mg/dL</span>
            </div>

            <br>

            <!-- Target blood sugar before the meal unit -->
            <div class="input-group">
              <input type="number" min="80" max="120" id="std_tbs" class="form-control"
                     placeholder="Target blood sugar before the meal unit" aria-describedby="sizing-addon4"
                     data-toggle="tooltip" data-placement="bottom" title="80 <= value <= 120"
                     onkeypress="return isValidNumeric(event)" onchange="enableButtonStandard()"
                     onkeyup="enableButtonStandard()" onblur="enableButtonStandard()">
              <span class="input-group-addon" id="sizing-addon4">mg/dL</span>
            </div>

            <br>

            <!-- Individual sensitivity -->
            <!-- default = 50 -->
            <div class="input-group">
              <input type="number" min="15" max="100" id="std_is" class="form-control"
                     placeholder="Individual sensitivity" aria-describedby="sizing-addon5" data-toggle="tooltip"
                     data-placement="bottom" title="15 <= value <= 100 (default = 50)"
                     onkeypress="return isValidNumeric(event)" onchange="enableButtonStandard()"
                     onkeyup="enableButtonStandard()" onblur="enableButtonStandard()">
              <span class="input-group-addon" id="sizing-addon5">unit</span>
            </div>

          </div>

          <div id="personal-insulin">
            <h4>Personal Insulin Dose</h4>

            <!-- Total grams of carbohydrates in the meal -->
            <div class="input-group">
              <input type="number" min="60" max="120" id="prs_tgcm" class="form-control"
                     placeholder="Total grams of carbohydrates in the meal" aria-describedby="sizing-addon6"
                     data-toggle="tooltip" data-placement="bottom" title="60 <= value <= 120"
                     onkeypress="return isValidNumeric(event)" onkeyup="enableButtonPersonal()"
                     onblur="enableButtonPersonal()" onchange="enableButtonPersonal()">
              <span class="input-group-addon" id="sizing-addon6">g</span>
            </div>

            <br>

            <!-- Total grams of carbohydrates processed by 1 unit of rapid acting insulin -->
            <!-- default = 12 -->
            <div class="input-group">
              <input type="number" min="10" max="15" id="prs_tgcp" class="form-control"
                     placeholder="Total grams of carbohydrates processed by 1 unit of rapid acting insulin"
                     aria-describedby="sizing-addon7" data-toggle="tooltip" data-placement="bottom"
                     title="10 <= value <= 15 (default = 12)" onkeypress="return isValidNumeric(event)"
                     onkeyup="enableButtonPersonal()" onblur="enableButtonPersonal()" onchange="enableButtonPersonal()">
              <span class="input-group-addon" id="sizing-addon7">g</span>
            </div>

            <br>

            <!-- Actual blood sugar level measured before the meal -->
            <div class="input-group">
              <input type="number" min="120" max="250" id="prs_abs" class="form-control"
                     placeholder="Actual blood sugar level measured before the meal"
                     aria-describedby="sizing-addon8" data-toggle="tooltip" data-placement="bottom"
                     title="120 <= value <= 250" onkeypress="return isValidNumeric(event)"
                     onkeyup="enableButtonPersonal()" onblur="enableButtonPersonal()" onchange="enableButtonPersonal()">
              <span class="input-group-addon" id="sizing-addon8">mg/dL</span>
            </div>

            <br>

            <!-- Target blood sugar before the meal unit -->
            <div class="input-group">
              <input type="number" min="80" max="120" id="prs_tbs" class="form-control"
                     placeholder="Target blood sugar before the meal unit" aria-describedby="sizing-addon9"
                     data-toggle="tooltip" data-placement="bottom" title="80 <= value <= 120"
                     onkeypress="return isValidNumeric(event)" onkeyup="enableButtonPersonal()"
                     onblur="enableButtonPersonal()" onchange="enableButtonPersonal()">
              <span class="input-group-addon" id="sizing-addon9">mg/dL</span>
            </div>

            <br>

            <!-- Today’s physical activity level -->
            <div class="input-group">
              <input type="number" min="0" max="10" id="prs_is" class="form-control"
                     placeholder="Today’s physical activity level" aria-describedby="sizing-addon11"
                     data-toggle="tooltip" data-placement="bottom" title="0 <= value <= 10"
                     onkeypress="return isValidNumeric(event)" onkeyup="enableButtonPersonal()"
                     onblur="enableButtonPersonal()" onchange="enableButtonPersonal()">
              <span class="input-group-addon" id="sizing-addon10">unit</span>
            </div>

            <br>

            <small>
              <b>Please fill, at least, two pairs of values per sample</b>
            </small>

            <br><br>

            <!-- samples -->
            <small>
                  <span class="glyphicon glyphicon-info-sign" data-toggle="tooltip" data-placement="bottom"
                        title="Between 0 (no physical activity) and 10 (very high physical activity)">
                  </span>
              Samples of physical activity level (between 0 and 10)
            </small>

            <br>

            <%
                int i;
                for(i=1; i<11; i++){
                    out.print("<input type=\"number\" min=\"0\" max=\"10\" step=\"1\" class=\"input-sample\" id=\"pal" + i + "\"" +
                            "onkeypress=\"return isValidNumeric(event)\" onkeyup=\"enableButtonPersonal()\" +" +
                            "onchange=\"enableButtonPersonal()\"" +
                            "onblur=\"enableButtonPersonal()\">");
                }
            %>

            <br><br>

            <small>
                  <span class="glyphicon glyphicon-info-sign" data-toggle="tooltip" data-placement="bottom"
                        title="15mg/dl <= value <= 100mg/dl">
                  </span>
              Samples of drops in blood sugar from one unit of insulin (between 15 and 100 mg/dl)
            </small>

            <br>

              <%
                  for(i=1; i<11; i++){
                      out.print("<input type=\"number\" min=\"15\" max=\"100\" step=\"1\" class=\"input-sample\" id=\"dbs" + i + "\"" +
                              "onkeypress=\"return isValidNumeric(event)\" onkeyup=\"enableButtonPersonal()\" +" +
                              "onchange=\"enableButtonPersonal()\"" +
                              "onblur=\"enableButtonPersonal()\">");
                  }
              %>

          </div>

          <div id="background-insulin">
            <h4>Background Insulin Dose</h4>

            <!-- Weight in kilograms -->
            <div class="input-group">
              <input type="number" min="40" max="130" name="bg_kg" id="bg_kg" class="form-control"
                     placeholder="Weight in kilograms" aria-describedby="sizing-addon11"
                     data-toggle="tooltip" data-placement="bottom" title="40 <= value <= 130"
                     onkeypress="return isValidNumeric(event)" onkeyup="enableButtonBackground()"
                     onblur="enableButtonBackground()" onchange="enableButtonBackground()" >
              <span class="input-group-addon" id="sizing-addon11">Kg</span>
            </div>

          </div>

        </div>

        <div class="col-md-4" style="text-align: center;">
          <h4>Calculate Insulin Dose</h4>
          <input type="button" id="submit_std" value="Calculate" name="submit_std" onclick="submitStandard();">
          <input type="button" id="submit_prs" value="Calculate" name="submit_prs" onclick="submitPersonal();">
          <input type="button" id="submit_bg" value="Calculate" name="submit_bg" onclick="submitBackground();">
        </div>
      </div>
    </div>
    <div class="col-md-2"></div>
  </div>

  <!-- window where it is showed the result of the calculation from the servlet side -->
  <div class="row" id="results-window">
    <div class="col-md-2"></div>
    <div class="col-md-8" id="results-col"></div>
    <div class="col-md-2"></div>
  </div>

</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="visual/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>

<script>
  $(document).ready(function() {

    // actions on click in each of 3 main tabs

    $("#btn-standard").click(function () {
      $("#standard-insulin").fadeIn("slow");
      $("#personal-insulin").fadeOut(1);
      $("#background-insulin").fadeOut(1);
      $("#main-window").css({display:"block"});
      $("#results-window").css({display:"none"});
      // buttons
      enableButtonStandard();
    });

    $("#btn-personal").click(function () {
      $("#standard-insulin").fadeOut(1);
      $("#personal-insulin").fadeIn("slow");
      $("#background-insulin").fadeOut(1);
      $("#main-window").css({display:"block"});
      $("#results-window").css({display:"none"});
      // buttons
      enableButtonPersonal();
    });

    $("#btn-background").click(function () {
      $("#standard-insulin").fadeOut(1);
      $("#personal-insulin").fadeOut(1);
      $("#background-insulin").fadeIn("slow");
      $("#main-window").css({display:"block"});
      $("#results-window").css({display:"none"});
      // buttons
      enableButtonBackground();
    });

    // actions to auto fill some fields

    // Total grams of carbohydrates processed by 1 unit of rapid acting insulin (standard and personal)
    $("#std_tgcp").focusout(function(){
      if(this.value==""){
        this.value="12"
      }
    });

    $("#prs_tgcp").focusout(function(){
      if(this.value==""){
        this.value="12"
      }
    });

    // Individual sensitivity (standard)
    $("#std_is").focusout(function(){
      if(this.value==""){
        this.value="50"
      }
    });

    // Tooltips to help with the usual values
    $(function () {
      $('[data-toggle="tooltip"]').tooltip()
    });

  });

  // verify if it is numeric; it still allows special characters
  function isValidNumeric(evt){
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

    return true;
  }

  // check if a value is between a min and max
  function isInRange(x, min, max){
    if(parseInt(x)>=min && parseInt(x)<=max)
      return true;

    return false
  }

  // check samples (personal insulin sensitivity)
  function checkSamples(){

    // the first 2 fields of each group must be filled to enable the button that calculates personal insulin
    if($("#pal1").val()!="" && $("#pal2").val()!=""
            && $("#dbs1").val()!="" && $("#dbs2").val()!=""){
      // it only allows equivalent pairs of values (physical activity and drops in blood sugar)
      for(i=3; i<11; i++){
        if ( ($("#pal" + i).val().length>0 && $("#dbs" + i).val().length ==0)
                || ($("#dbs" + i).val().length>0 && $("#pal" + i).val().length == 0)) {
          return false;
        }
        // the pair of values must be inside the limits
        else if( ($("#pal" + i).val().length>0 && $("#dbs" + i).val().length>0) ){
          if(!isInRange($("#pal" + i).val(),0,10) || !isInRange($("#dbs" + i).val(),15,100)){
            return false;
          }
        }
      }

    }

    return true;

  }

  // check when the buttons should be enable
  function enableButtonStandard() {
    if (isInRange($('#std_tgcm').val(), 60, 120)
            && isInRange($('#std_tgcp').val(), 10, 15)
            && isInRange($('#std_abs').val(), 120, 250)
            && isInRange($('#std_tbs').val(), 80, 120)
            && isInRange($('#std_is').val(), 15, 100)
    ) {
      $("#submit_std").css({display: "block"});
      $("#submit_prs").css({display: "none"});
      $("#submit_bg").css({display: "none"});
    } else {
      $("#submit_std").css({display: "none"});
      $("#submit_prs").css({display: "none"});
      $("#submit_bg").css({display: "none"});
    }
  }

  function enableButtonPersonal() {
    if(isInRange($('#prs_tgcm').val(), 60, 120)
            && isInRange($('#prs_tgcp').val(), 10, 15)
            && isInRange($('#prs_abs').val(), 120, 250)
            && isInRange($('#prs_tbs').val(), 80, 120)
            && isInRange($('#prs_is').val(), 0, 10)
            && checkSamples()
            && isInRange($("#pal1").val(),0,10)
            && isInRange($("#pal2").val(),0,10)
            && isInRange($("#dbs1").val(),15,100)
            && isInRange($("#dbs2").val(),15,100)
    ){
      $("#submit_prs").css({display: "block"});
      $("#submit_std").css({display: "none"});
      $("#submit_bg").css({display: "none"});
    }else{
      $("#submit_std").css({display: "none"});
      $("#submit_prs").css({display: "none"});
      $("#submit_bg").css({display: "none"});
    }

  }

  function enableButtonBackground() {
    if(isInRange($('#bg_kg').val(), 40, 130)){
      $("#submit_prs").css({display: "none"});
      $("#submit_std").css({display: "none"});
      $("#submit_bg").css({display: "block"});
    }else{
      $("#submit_std").css({display: "none"});
      $("#submit_prs").css({display: "none"});
      $("#submit_bg").css({display: "none"});
    }

  }

  function submitStandard(){
    var dataString = {"FLAG":"standard",
      "std_tgcm":$("#std_tgcm").val(),
      "std_tgcp":$("#std_tgcp").val(),
      "std_abs":$("#std_abs").val(),
      "std_tbs":$("#std_tbs").val(),
      "std_is":$("#std_is").val()
    };

    ajaxConnection("Standard", dataString);

  }

  function submitPersonal(){
    // Samples of physical activity level in a given day
    var sample_pal = new Array();
    // Samples of drops in blood sugar from one unit of insulin in that day
    var sample_dbs = new Array();

    for (i = 1; i < 11; i++) {
      if( ($("#pal" + i).val() != '') && ($("#dbs" + i).val() != '') ){
        sample_pal.push($("#pal" + i).val());
        sample_dbs.push($("#dbs" + i).val());
      }
    }

    var dataString = {"FLAG":"personal",
      "prs_tgcm":$("#prs_tgcm").val(),
      "prs_tgcp":$("#prs_tgcp").val(),
      "prs_abs":$("#prs_abs").val(),
      "prs_tbs":$("#prs_tbs").val(),
      "prs_is":$("#prs_is").val(),
      "sample_pal":JSON.stringify(sample_pal),
      "sample_dbs":JSON.stringify(sample_dbs)
    };

    ajaxConnection("Personal", dataString);
  }

  function submitBackground(){

    var dataString = {"FLAG":"background",
      "bg_kg":$("#bg_kg").val()};

    ajaxConnection("Background", dataString);

  }

  function ajaxConnection(insulinType, dataString){

    $.ajax({
      type: "POST",
      data: dataString,
      url: "servlets/Servlet",
      success:function(data) {

        if (data != null){

          result = JSON.parse(data);

          var webservices = result.num_webservices;
          var results = result.results;
          var majority = result.majority_result;

          // clear all input fields (exception for buttons)
          $(':input','#main-window')
                  .not(':button')
                  .val('');

          // hide the inputs window
          $("#main-window").css({display:"none"});

          // todo e se nao vier um resultado? ver o codigo de erro e notificar user

          var code_to_print = '<h3>Insulin Dose for ' + insulinType + ' Insulin Sensitivity<h3>' +
                  '<br>';
                  if(majority == -2){
                    code_to_print += '<h1>Time out</h1>';
                  }
                  else if(majority == -3) {
                    code_to_print += '<h1>Invalid input. Try again.</h1>';
                  }
                  else if(majority == -4) {
                    code_to_print += '<h1>A wild Exception appeared. Please, try again.</h1>';
                  }
                  else
                  {
                    code_to_print += '<h1>' + majority + '</h1>';
                    code_to_print += '<button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-sm">Technical Detail</button>' +
                            '<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">' +
                            '<div class="modal-dialog modal-sm">' +
                            '<div class="modal-content">' +
                            '<h3>Technical Detail</h3><br>' +
                            '<b>Number of web services:</b> ' + webservices + '<br><br>';

                            // print the value of each web service
                    $.each(results, function(i,r){
                      code_to_print += '<b>Web service ' + i + ':</b> ' + r + '<br>';
                    });

                    code_to_print += '<br><b>Majority result: </b>' + majority;
                    code_to_print += '<br><br><small><b>-1:</b> Exception raised<br><b>-2:</b> Time out</small>';
                    code_to_print += '<br><br><button type="button" class="btn btn-default" data-dismiss="modal">Close</button><br><br>';
                    code_to_print +='</div>' +
                            '</div>' +
                            '</div>';
                  }

          // clean the window content
          $("#results-col").empty();
          // fill with insulin dose and technical detail
          $("#results-col").append(code_to_print);

          $("#results-window").css({display:"block"});

        }
      }
    });
  }

</script>
</body>

</html>
