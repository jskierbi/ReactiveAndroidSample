/*
 * (c) Neofonie Mobile GmbH (2015)
 *
 * This computer program is the sole property of Neofonie Mobile GmbH (http://mobile.neofonie.de)
 * and is protected under the German Copyright Act (paragraph 69a UrhG).
 *
 * All rights are reserved. Making copies, duplicating, modifying, using or distributing
 * this computer program in any form, without prior written consent of Neofonie Mobile GmbH, is prohibited.
 * Violation of copyright is punishable under the German Copyright Act (paragraph 106 UrhG).
 *
 * Removing this copyright statement is also a violation.
 */
package com.jskierbi.reactiveandroidsample.Network;

import rx.Observable;

/**
 * Created by jakub on 22/05/15.
 */
public class CachedObservable<T> extends Observable<T> {

  /**
   * Creates an Observable with a Function to execute when it is subscribed to.
   * <p/>
   * <em>Note:</em> Use {@link #create(OnSubscribe)} to create an Observable, instead of this constructor,
   * unless you specifically have a need for inheritance.
   *
   * @param f {@link OnSubscribe} to be executed when {@link #subscribe(Subscriber)} is called
   */
  protected CachedObservable(OnSubscribe<T> f) {

    // Adds means to
    super(f);
  }

  public static <ST> CachedObservable<ST> just(Observable<ST> observable, String cacheKey) {
    return null;
  }
}
