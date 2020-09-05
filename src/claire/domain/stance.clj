(ns claire.domain.stance
  (:require [claire.adapt.db :as db]))

(defn ensure-table []
  (let 
   [sql (str "create table if not exists stance ("
             "id integer primary key, "
             "name text not null);")]
    (db/create-table! sql)))

(defn get-all []
  (db/query "select * from stance"))

(defn count-all []
  (db/query
   "select count(*) as count from stance"))

(defn ensure-preset []
  (let [preset [{:id 1 :name "payer"}
                {:id 2 :name "receiver"}
                {:id 3 :name "buyer"}
                {:id 4 :name "seller"}]]
    (if (>= (:count (first (count-all)))
            (count preset))
      ()
      (doseq [item preset] 
        (db/insert! :stance item)))))

(defn set-db []
  (ensure-table)
  (ensure-preset)
  (println "<stance> table is set up."))