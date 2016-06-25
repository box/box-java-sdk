<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>Java Servlet Template</title>
    <link href='//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css' rel='stylesheet' type='text/css'>
    <link href='//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css' rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Open+Sans:100,200,400,300,600' rel='stylesheet' type='text/css'>
    <script src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script src='//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js'></script>
    <style>
        body{
            font-family:"Open Sans",sans-serif;
            background-color:#f9f9f9;
            color: #666;
            font-weight: 300;
            }
        h1{font-weight: 300;}
        h3{font-weight: 300;}
        .key{text-align: right; font-weight: bold;}
        .value{font-family: "Courier";}
        .navbar-inverse {
            background-color: #3278cf;
            border-color: #3278cf;
            border-radius: 0px
        }
        .navbar-inverse .navbar-brand {
            font-size: 150%;
            color: #fff
        }
        .navbar-inverse .username {
            margin-top: 14px;
            text-align: right;
            color: #fff
        }
        .navbar-inverse .username img {
            margin-top: -3px
        }
        .footer {
            height: 50px;
            text-align: right;
            font-size: 120%;
            color: #DDD
        }
        .footer .navbar {background-color: #333}
        .footer .navbar .logout {margin-top: 12px}
        .footer .navbar .logout a {color: #fff}
        .btn-primary {
            background-color: #3278cf;
            border-color: #3278cf
        }
        .signin-button {margin-top: 100px}
        .modal-dialog {
            height: 800px;
            width: 960px;
            background-color: white;
        }
        .modal-dialog .modal-body {height: 730px; width: 960px}
        .modal-dialog .modal-footer .btn {font-size: 100%;}
    </style>
  </head>
  <body>
      <nav class='navbar navbar-inverse'>
        <div class='container'>
          <div class='navbar-header'>
            <a class='navbar-brand' href='/dashboard'>
              Box Platform Java Example
            </a>
          </div>

        </div>
      </nav>
    <div class="container">
