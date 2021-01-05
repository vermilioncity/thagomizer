(ns thagomizer.db)

(def default-db {:passcode nil
                 :admin nil
                 :chat {:authenticated nil
                        :uid nil
                        :text-field ""
                        :is-typing {:self false
                                    :others #{}}
                        :uids {}
                        :messages #queue []
                        :camera {:stream {:element nil
                                          :object nil
                                          :visible false
                                          :error nil
                                          :loading nil}
                                 :photo {:url nil
                                         :error nil
                                         :loading nil}
                                 :modal false}}
                 :send {:authenticated nil
                        :text-field ""}
                 :receipt  {:authenticated nil
                            :messages {:data []
                                       :error nil}}})