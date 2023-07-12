(ns freshers-onboarding-2023.core
  (:gen-class)
  (:require [freshers-onboarding-2023.utils.quiz :refer [start-quiz]]))

(defn -main
  "I don't do a whole lot ... yet. Or do I?"
  [& _]
  (start-quiz 5))
