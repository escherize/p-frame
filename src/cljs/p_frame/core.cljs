(ns p-frame.core
    (:require [reagent.core :as reagent]
              [devtools.core :as devtools]
              [p-frame.subs]
              [p-frame.views :as views]
              [p-frame.config :as config]))

(defn dev-setup []
  (when config/debug? (println "dev mode") (devtools/install!)))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (dev-setup)
  (mount-root))
