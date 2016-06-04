(ns p-frame.db
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [reagent.core :as r]
            [re-frame.frame :as frame]
            [re-frame.router :as router]
            [p-frame.handlers :refer [handlers]]))

(def default-db {:name "re-frame"})

(def app-db (r/atom default-db))

(def subscriptions
  (let [db app-db]
    {:name (fn [& xs]
             (println xs)
             (reaction (:name @app-db)))}))

(def ^:private app-frame (frame/make-frame handlers subscriptions))
(defonce ^:private event-queue (router/make-event-queue app-frame app-db))

(def dispatch (partial router/dispatch event-queue app-frame))
(def subscribe (partial frame/subscribe app-frame))

@(subscribe [:name])
;;=> (:name)
