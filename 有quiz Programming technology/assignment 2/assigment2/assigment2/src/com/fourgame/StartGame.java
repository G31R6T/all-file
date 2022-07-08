package com.fourgame;

import com.fourgame.board.createBoard;

/**
 * @描叙:
 * @作者: mzdora
 * @邮箱: mzdora@qq.com
 * @创建时间: 2021/11/8 7:30 下午
 **/
public class StartGame {
    /**
     * The only purpose of the main method is to call the picklevel method in class createboard.
     * @param args
     */
    public static void main(String[] args)
    {
        createBoard.pickLevel();
    }
}
