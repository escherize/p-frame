(ns p-frame.db
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [reagent.core :as r]
            [re-frame.frame :as frame]
            [re-frame.router :as router]
            [re-frame.utils :as rf-util]
            [re-frame.logging :as logging]
            [p-frame.handlers :as hands]
            [p-frame.subs :as subs]))

(def log (fn [& xs] (apply #(js/console.log %) xs)))

(def default-db {:name (gensym "re-frame")})

(def app-db (r/atom default-db))

(def app-frame (frame/make-frame hands/handlers subs/subscriptions logging/default-loggers))

(def event-queue (router/make-event-queue app-frame app-db))

(def dispatch (partial router/dispatch event-queue app-frame))

(defn- subscribe-app-db
  "Returns a reagent/reaction which observes state."
  [frame app-db subscription-spec]
  (let [subscription-id (rf-util/get-subscription-id subscription-spec)
        handler-fn (get-in frame [:subscriptions subscription-id])]
    (if (nil? handler-fn)
      (re-frame.logging/error frame
                              "re-frame: no subscription handler registered for: \"" subscription-id "\".  Returning a nil subscription.")
      (handler-fn app-db subscription-spec))))

(defn subscribe [subscribe-v]
  (subscribe-app-db app-frame app-db subscribe-v))

(assert @(subscribe [:name]) "Subscription is busted.")
