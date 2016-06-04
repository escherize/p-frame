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

(defn create-app-frame
  ([] (create-app-frame (frame/make-frame nil nil)))
  ([frame]
   (-> frame
       (frame/register-event-handler :initialize-db (fn  [_ _] default-db))
       (frame/register-event-handler :set-kv (fn  [db [_ k v]] (assoc db k v)))
       (frame/register-subscription-handler
        :name (partial (fn [db v]
                         (let [db-value @db]

                           )) app-db)))))

(def app-frame (create-app-frame))

(defn dispatch
  ([dispatch-v]
   (reset! app-db (frame/process-event app-frame @app-db dispatch-v)))
  ([app-db dispatch-v]
   (reset! app-db (frame/process-event app-frame @app-db dispatch-v))))

(defn subscribe [subscribe-v]
  (reaction (frame/subscribe app-frame subscribe-v)))

(comment

  (dispatch [:initialize-db])

  (dispatch [:set-kv :name (gensym "lemon")])

  @(subscribe [:name])

  )
