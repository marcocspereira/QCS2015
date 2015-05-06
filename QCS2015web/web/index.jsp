<%--
  Created by IntelliJ IDEA.
  User: Marco
  Date: 4/27/15
  Time: 11:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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


    <div class="row">
    <form method="post">
      <div class="col-md-2"></div>
      <div class="col-md-8 col-md-body">
        <div class="row">
          <div class="col-md-8 col-md-menu">
            <div id="standard-insulin">
              <h4>Standard Insulin Sensitivity</h4>

              <!-- Total grams of carbohydrates in the meal -->
              <div class="input-group">
                <input type="number" min="60" max="120" id="std_tgcm" class="form-control" placeholder="Total grams of carbohydrates in the meal" aria-describedby="sizing-addon1" data-toggle="tooltip" data-placement="bottom" title="60 >= value <= 120" onkeypress="return isValidNumeric(event)" onkeyup="enableButtonStandard()" onblur="enableButton()">
                <span class="input-group-addon" id="sizing-addon1">g</span>
              </div>

              <br>

              <!-- Total grams of carbohydrates processed by 1 unit of rapid acting insulin -->
              <!-- default = 12 -->
              <div class="input-group">
                <input type="number" min="10" max="15" id="std_tgcp" class="form-control" placeholder="Total grams of carbohydrates processed by 1 unit of rapid acting insulin" aria-describedby="sizing-addon2"  data-toggle="tooltip" data-placement="bottom" title="10 >= value <= 15 (default = 12)" onkeypress="return isValidNumeric(event)" onkeyup="enableButtonStandard()" onblur="enableButton()">
                <span class="input-group-addon" id="sizing-addon2">g</span>
              </div>

              <br>

              <!-- Actual blood sugar level measured before the meal -->
              <div class="input-group">
                <input type="number" min="120" max="250" id="std_abs" class="form-control" placeholder="Actual blood sugar level measured before the meal" aria-describedby="sizing-addon3" data-toggle="tooltip" data-placement="bottom" title="120 >= value <= 250" onkeypress="return isValidNumeric(event)" onkeyup="enableButtonStandard()" onblur="enableButton()">
                <span class="input-group-addon" id="sizing-addon3">mg/dL</span>
              </div>

              <br>

              <!-- Target blood sugar before the meal unit -->
              <div class="input-group">
                <input type="number" min="80" max="120" id="std_tbs" class="form-control" placeholder="Target blood sugar before the meal unit" aria-describedby="sizing-addon4" data-toggle="tooltip" data-placement="bottom" title="80 >= value <= 120" onkeypress="return isValidNumeric(event)" onkeyup="enableButtonStandard()" onblur="enableButton()">
                <span class="input-group-addon" id="sizing-addon4">mg/dL</span>
              </div>

              <br>

              <!-- Individual sensitivity -->
              <!-- default = 50 -->
              <div class="input-group">
                <input type="number" min="15" max="100" id="std_is" class="form-control" placeholder="Individual sensitivity" aria-describedby="sizing-addon5" data-toggle="tooltip" data-placement="bottom" title="15 >= value <= 100 (default = 50)" onkeypress="return isValidNumeric(event)" onkeyup="enableButtonStandard()" onblur="enableButton()">
                <span class="input-group-addon" id="sizing-addon5">unit</span>
              </div>

            </div>

            <div id="personal-insulin">
              <h4>Personal Insulin Dose</h4>

              <!-- Total grams of carbohydrates in the meal -->
              <div class="input-group">
                <input type="number" min="60" max="120" id="prs_tgcm" class="form-control" placeholder="Total grams of carbohydrates in the meal" aria-describedby="sizing-addon6" data-toggle="tooltip" data-placement="bottom" title="60 >= value <= 120" onkeypress="return isValidNumeric(event)" onkeyup="enableButtonPersonal()" onblur="enableButton()">
                <span class="input-group-addon" id="sizing-addon6">g</span>
              </div>

              <br>

              <!-- Total grams of carbohydrates processed by 1 unit of rapid acting insulin -->
              <!-- default = 12 -->
              <div class="input-group">
                <input type="number" min="10" max="15" id="prs_tgcp" class="form-control" placeholder="Total grams of carbohydrates processed by 1 unit of rapid acting insulin" aria-describedby="sizing-addon7"  data-toggle="tooltip" data-placement="bottom" title="10 >= value <= 15 (default = 12)" onkeypress="return isValidNumeric(event)" onkeyup="enableButtonPersonal()" onblur="enableButton()">
                <span class="input-group-addon" id="sizing-addon7">g</span>
              </div>

              <br>

              <!-- Actual blood sugar level measured before the meal -->
              <div class="input-group">
                <input type="number" min="120" max="250" id="prs_abs" class="form-control" placeholder="Actual blood sugar level measured before the meal" aria-describedby="sizing-addon8" data-toggle="tooltip" data-placement="bottom" title="120 >= value <= 250" onkeypress="return isValidNumeric(event)" onkeyup="enableButtonPersonal()" onblur="enableButton()">
                <span class="input-group-addon" id="sizing-addon8">mg/dL</span>
              </div>

              <br>

              <!-- Target blood sugar before the meal unit -->
              <div class="input-group">
                <input type="number" min="80" max="120" id="prs_tbs" class="form-control" placeholder="Target blood sugar before the meal unit" aria-describedby="sizing-addon9"data-toggle="tooltip" data-placement="bottom" title="80 >= value <= 120" onkeypress="return isValidNumeric(event)" onkeyup="enableButtonPersonal()" onblur="enableButton()">
                <span class="input-group-addon" id="sizing-addon9">mg/dL</span>
              </div>

              <br>

              <!-- Today’s physical activity level -->
              <!-- default = 50 -->
              <div class="input-group">
                <input type="number" min="15" max="100" id="prs_is" class="form-control" placeholder="Today’s physical activity level" aria-describedby="sizing-addon11" data-toggle="tooltip" data-placement="bottom" title="15 >= value <= 100 (default = 50)" onkeypress="return isValidNumeric(event)" onkeyup="enableButtonPersonal()" onblur="enableButton()">
                <span class="input-group-addon" id="sizing-addon10">unit</span>
              </div>

              <br>

              <!-- samples -->
              <small>Samples of physical activity level</small>

              <br>

              <input type="text" class="input-sample" id="pal1">
              <input type="text" class="input-sample" id="pal2">
              <input type="text" class="input-sample" id="pal3">
              <input type="text" class="input-sample" id="pal4">
              <input type="text" class="input-sample" id="pal5">
              <input type="text" class="input-sample" id="pal6">
              <input type="text" class="input-sample" id="pal7">
              <input type="text" class="input-sample" id="pal8">
              <input type="text" class="input-sample" id="pal9">
              <input type="text" class="input-sample" id="pal10">

              <br><br>

              <small>Samples of drops in blood sugar from one unit of insulin</small>

              <br>

              <input type="text" class="input-sample" id="dbs1">
              <input type="text" class="input-sample" id="dbs2">
              <input type="text" class="input-sample" id="dbs3">
              <input type="text" class="input-sample" id="dbs4">
              <input type="text" class="input-sample" id="dbs5">
              <input type="text" class="input-sample" id="dbs6">
              <input type="text" class="input-sample" id="dbs7">
              <input type="text" class="input-sample" id="dbs8">
              <input type="text" class="input-sample" id="dbs9">
              <input type="text" class="input-sample" id="dbs10">

            </div>

            <div id="background-insulin">
              <h4>Background Insulin Dose</h4>

              <!-- Weight in kilograms -->
              <div class="input-group">
                <input type="number" min="40" max="130" id="bg_kg" class="form-control" placeholder="Weight in kilograms" aria-describedby="sizing-addon11" data-toggle="tooltip" data-placement="bottom" title="40 >= value <= 130" onkeypress="return isValidNumeric(event)" onkeyup="enableButtonBackground()" onblur="enableButtonBackground()">
                <span class="input-group-addon" id="sizing-addon11">Kg</span>
              </div>

            </div>

          </div>

          <div class="col-md-4" style="text-align: center;">
            <h4>Calculate Insulin Dose</h4>
              <input type="submit" id="submit_std" value="Calculate">
              <input type="submit" id="submit_prs" value="Calculate">
              <input type="submit" id="submit_bg" value="Calculate">
          </div>
        </div>
      </div>
      <div class="col-md-2"></div>
    </form>
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
          // buttons
          enableButtonStandard();
        });

        $("#btn-personal").click(function () {
          $("#standard-insulin").fadeOut(1);
          $("#personal-insulin").fadeIn("slow");
          $("#background-insulin").fadeOut(1);
          // buttons
          enableButtonPersonal();
        });

        $("#btn-background").click(function () {
          $("#standard-insulin").fadeOut(1);
          $("#personal-insulin").fadeOut(1);
          $("#background-insulin").fadeIn("slow");
          // buttons
          enableButtonBackground();
        });

        // actions to auto fill some fields

          // Total grams of carbohydrates processed by 1 unit of rapid acting insulin
          $("#std_tgcp").focusout(function(){
              if(this.value==""){
                this.value="12"
              }
          });

          // Individual sensitivity
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
                  && isInRange($('#prs_is').val(), 15, 100)
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

  </script>
  </body>

</html>
