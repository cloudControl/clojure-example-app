(ns sample.app
  (:use compojure.core)  
  (:use ring.middleware.resource)  
  (:require 
          [ring.adapter.jetty :as jetty]
          [compojure.route :as route]
          [compojure.handler :as handler]))

(defroutes main-routes    
  (GET "/" [] "<!doctype html>
<html>
<head>
	<link href='helloworld.css' rel='stylesheet' type='text/css'/>
	<title>Hello World in Clojure</title>
</head>
<body>
	<strong>Hello world, I'm a Clojure app running on cloudControl!</strong>
  <footer>
    Coming from the <a href='https://www.cloudcontrol.com/dev-center/Quickstart'>Quickstart</a>?
    Check out <a href='https://www.cloudcontrol.com/dev-center/Guides/Java/Clojure'>the explanation</a>.
  </footer>
</body>
</html>")
  (route/resources "/")
  (route/not-found "<h1>Page not found</h1>"))

(def app  
  (handler/site main-routes))

(defn -main []
  (jetty/run-jetty main-routes
                   {:port (Integer. (or (System/getenv "PORT") "8080"))
                    :join? false}))
