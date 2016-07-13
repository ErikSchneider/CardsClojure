(ns cards-clojure.core
  (:gen-class))

(def suits [:clubs :spades :hearts :diamonds])
(def ranks (range 1 14))

(defn create-deck []
  (set
    (for [suit suits
          rank ranks]
      {:suit suit :rank rank})))

(defn create-hands [deck]
  (set
    (for [c1 deck
          c2 (disj deck c1)
          c3 (disj deck c1 c2)
          c4 (disj deck c1 c2 c3)]
      #{c1 c2 c3 c4})))

(defn flush? [hand]
  (let [suits (set (map :suit hand))]
    (= 1 (count suits))))

(defn straight? [hand]
  (let [sorted (sort (map :rank hand))
        smallest (first sorted)]
    (= sorted (range smallest (+ smallest 4)))))

(defn straight-flush? [hand]
  (and (flush? hand) (straight? hand)))

(defn four-of-a-kind? [hand]
  (= 1 (count (set (map :rank hand)))))

(defn -main []
  (let [deck (create-deck)
        hands (create-hands deck) 
        straights (filter four-of-a-kind? hands)]
    (count straights)))
