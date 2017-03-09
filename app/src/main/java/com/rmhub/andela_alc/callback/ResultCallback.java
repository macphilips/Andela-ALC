package com.rmhub.andela_alc.callback;

import com.rmhub.andela_alc.interfaces.HeaderInfo;

/**
 * Created by MOROLANI on 3/6/2017
 * <p>
 * owm
 * .
 */

public interface ResultCallback {

    void searchResult(String result);

    HeaderInfo getHeader();
}
