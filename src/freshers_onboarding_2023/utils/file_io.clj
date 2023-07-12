(ns freshers-onboarding-2023.utils.file-io
  (:require [cheshire.core :refer [parse-string-strict]]
            [clojure.core :refer [slurp]]))

(defn load-questions-and-answers
  "Loads question-answers from file

   Result: [{:id <question id (0-..)>
             :question 'some question'
             :options ['option' ..]
             :answer <option index (0-3)>}]"
  []
  ;; FIXME Something seems off here
  ;; hint: keyword
  (-> "resources/questions_and_answers.json"
      slurp
      parse-string-strict))
