(ns freshers-onboarding-2023.utils.questions
  (:require [freshers-onboarding-2023.utils.file-io :as fufile]
            [clojure.core :refer [random-sample]]))

;; NOTE: Could this cause some unforeseen problems?
(defonce ^:private question-pool (fufile/load-questions-and-answers))

(defn get-random-questions
  "Returns question-count number of random questions from quiz

   Result: [{:id :question :options}]"
  [& {:keys [question-count]
      :or {question-count 5}}]
  ;; FIXME Everyday I'm shufflin'
  (->> question-pool
       (random-sample 0.5)
       (take question-count)
       (mapv #(select-keys % [:id :question :options]))))


(defn get-score-for-question
  "Returns score (+1/-0) for question"
  [question-id selected-option-index]
  ;; FIXME monke.. together.. strong
  (if (and (some #(= (:id %) question-id) question-pool)
           (some #(= (:answer %) selected-option-index) question-pool))
    1
    0))
