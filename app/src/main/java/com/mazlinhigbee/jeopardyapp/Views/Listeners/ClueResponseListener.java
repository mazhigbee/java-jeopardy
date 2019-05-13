package com.mazlinhigbee.jeopardyapp.Views.Listeners;

import com.mazlinhigbee.jeopardyapp.Models.Clue;

/**
 * com.mazlinhigbee.jeopardyapp.Views.Listeners
 * Created by: mhigbee
 * Date: 5/12/19 Time: 7:18 PM
 */
public interface ClueResponseListener {
    void correct(Clue clue);
    void incorrect(Clue clue);
}
