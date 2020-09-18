(ns claire.domain.basis.day
  (:require [claire.center.db :as db]))

(defn schema []
  "create table if not exists 
   day (
   id smallserial primary key, 
   name text unique not null, 
   memo text not null)")

(defn preval []
  [{:id 1 :name "30360" :memo ".."}
   {:id 2 :name "AC360" :memo ".."}]) 

(defn set-db! []
  (db/execute! (schema))
  (db/insert-pre! :day (preval))
  (println "<day> table is set up."))