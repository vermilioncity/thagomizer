(ns thagomizer.common.components.input
  (:require
   [re-frame.core :as rf]))

(defn target-value
  "Extracts the value of the event fired."
  [event]
  (.-value (.-target event)))

(defn handle-enter-press
  "We want to send the message when the user hits enter, but add a 
   paragraph when they hit shift+enter.  Otherwise, behave as normal!"
  [e dispatch-shift-enter-event dispatch-enter-event]
  (let [key-num (or (.-keyCode e) (.-which e))
        shift   (.-shiftKey e)
        enter   13]
    (cond
      (and (= enter key-num) shift)
      (do
        (.preventDefault e)
        (rf/dispatch [dispatch-shift-enter-event (str (target-value e) "\n")]))
      (= enter key-num)
      (do (.preventDefault e)
          (rf/dispatch [dispatch-enter-event]))
      :else
      "default")))

(defn on-text-field-value-change
  "Sets the text field with the value submitted in the event"
  [update-event e]
  (rf/dispatch [update-event (target-value e)]))


(defn button
  [event key value & {:keys [elm-overrides style-overrides]}]
  (let [style-defaults {:font-size 11
                        :font-family "Gloria Hallelujah, cursive"
                        :border "none"
                        :text-align "center"
                        :border-radius "15px"
                        :padding "5px 8px 5px 8px"
                        :margin "5px"
                        :display "inline-block"
                        :background-color "#EFEFEF"}
        elm-defaults {:type "submit"
                      :value value
                      :onClick #(rf/dispatch event)
                      :key key}]

    [:button (assoc (merge elm-defaults elm-overrides)
                       :style (merge style-defaults style-overrides))
     value]))
