(ns bootcamp.hello-cljs-world)

(defn hello-world []
  (js/console.log "Hello, ClojureScript world!"))

(set! *main-cli-fn* hello-world)
