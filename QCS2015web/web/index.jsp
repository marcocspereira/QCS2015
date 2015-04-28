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
      <div class="col-md-2"></div>
      <div class="col-md-8 col-md-body">
        <div class="row">
          <div class="col-md-8 col-md-menu">
            <div id="standard-insulin">
              <h4>Standard Insulin Sensitivity</h4>

              <!-- Total grams of carbohydrates in the meal -->
              <div class="input-group">
                <input type="text" class="form-control" placeholder="Total grams of carbohydrates in the meal" aria-describedby="sizing-addon1">
                <span class="input-group-addon" id="sizing-addon1">g</span>
              </div>

              <br>

              <!-- Total grams of carbohydrates processed by 1 unit of rapid acting insulin -->
              <!-- TODO: começa com valor = 12 -->
              <div class="input-group">
                <input type="text" class="form-control" placeholder="Total grams of carbohydrates processed by 1 unit of rapid acting insulin" aria-describedby="sizing-addon2">
                <span class="input-group-addon" id="sizing-addon2">g</span>
              </div>

              <br>

              <!-- Actual blood sugar level measured before the meal -->
              <!-- TODO: confirmar as unidades -->
              <div class="input-group">
                <input type="text" class="form-control" placeholder="Actual blood sugar level measured before the meal" aria-describedby="sizing-addon3">
                <span class="input-group-addon" id="sizing-addon3">mg/dL</span>
              </div>

              <br>

              <!-- Target blood sugar before the meal unit -->
              <!-- TODO: confirmar as unidades -->
              <div class="input-group">
                <input type="text" class="form-control" placeholder="Target blood sugar before the meal unit" aria-describedby="sizing-addon4">
                <span class="input-group-addon" id="sizing-addon4">mg/dL</span>
              </div>

              <br>

              <!-- Individual sensitivity -->
              <!-- TODO: começa com valor = 50 -->
              <!-- TODO: confirmar as unidades -->
              <div class="input-group">
                <input type="text" class="form-control" placeholder="Individual sensitivity" aria-describedby="sizing-addon5">
                <span class="input-group-addon" id="sizing-addon5">unit</span>
              </div>

            </div>

            <div id="personal-insulin">
              <h4>Personal Insulin Dose</h4>

              <!-- Total grams of carbohydrates in the meal -->
              <div class="input-group">
                <input type="text" class="form-control" placeholder="Total grams of carbohydrates in the meal" aria-describedby="sizing-addon6">
                <span class="input-group-addon" id="sizing-addon6">g</span>
              </div>

              <br>

              <!-- Total grams of carbohydrates processed by 1 unit of rapid acting insulin -->
              <!-- TODO: começa com valor = 12 -->
              <div class="input-group">
                <input type="text" class="form-control" placeholder="Total grams of carbohydrates processed by 1 unit of rapid acting insulin" aria-describedby="sizing-addon7">
                <span class="input-group-addon" id="sizing-addon7">g</span>
              </div>

              <br>

              <!-- Actual blood sugar level measured before the meal -->
              <!-- TODO: confirmar as unidades -->
              <div class="input-group">
                <input type="text" class="form-control" placeholder="Actual blood sugar level measured before the meal" aria-describedby="sizing-addon8">
                <span class="input-group-addon" id="sizing-addon8">mg/dL</span>
              </div>

              <br>

              <!-- Target blood sugar before the meal unit -->
              <!-- TODO: confirmar as unidades -->
              <div class="input-group">
                <input type="text" class="form-control" placeholder="Target blood sugar before the meal unit" aria-describedby="sizing-addon9">
                <span class="input-group-addon" id="sizing-addon9">mg/dL</span>
              </div>

              <br>

              <!-- Today’s physical activity level -->
              <!-- TODO: confirmar as unidades -->
              <div class="input-group">
                <input type="text" class="form-control" placeholder="Today’s physical activity level" aria-describedby="sizing-addon11">
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
                <input type="text" class="form-control" placeholder="Weight in kilograms" aria-describedby="sizing-addon11">
                <span class="input-group-addon" id="sizing-addon11">Kg</span>
              </div>

            </div>

          </div>
          <div class="col-md-4">
            <h4>Calculate Insulin Dose</h4>
          </div>
        </div>
      </div>
      <div class="col-md-2"></div>
    </div>
  </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="visual/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>

    <script>
      $(document).ready(function() {
        $("#btn-standard").click(function () {
          $("#standard-insulin").fadeIn("slow");
          $("#personal-insulin").fadeOut(1);
          $("#background-insulin").fadeOut(1);
        });

        $("#btn-personal").click(function () {
          $("#standard-insulin").fadeOut(1);
          $("#personal-insulin").fadeIn("slow");
          $("#background-insulin").fadeOut(1);
        });

        $("#btn-background").click(function () {
          $("#standard-insulin").fadeOut(1);
          $("#personal-insulin").fadeOut(1);
          $("#background-insulin").fadeIn("slow");
        });
      });
  </script>
  </body>


</html>
