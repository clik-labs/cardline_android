package kr.edcan.cardline.utils;

import android.util.Pair;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Conota on 2017-07-04.
 * - 단일 페이지 통합 관리자
 */

public class PageExt {
    private File main_img;
    private String main_img_name;
    private int page;
    private Pair<Integer, Integer> res_count;
    private File res_back = null;
    private String res_back_name;
    private ArrayList<ResManager> res_img = new ArrayList<>();
    private ArrayList<ResManager> res_txt = new ArrayList<>();

    public PageExt() {

    }

    public PageExt(String s) {

    }

    /**
     * @param page {
     *             "main_img": "link1~~~~~~~",	// 이미지와 텍스트로 구성된 카드 뉴스 NULL 불가
     *             "page": 1,
     *             "res_count": [2, 0], // images / text	0 -> null
     *             "res_back": "rsss.png",	// Background Resource
     *             "res_img": [
     *             ["img1", "x", "y", "width", "height"],
     *             ["img2", "x", "y", "width", "height"]
     *             ],
     *             "res_txt": [ ]
     *             }
     *             <p>
     *             Page JSON 구조
     */

    public void setPage(int page) {
        this.page = page;
    }

    public void setResCount(Pair<Integer, Integer> res_count) {
        this.res_count = res_count;
    }

    public void setMainImg(File img) {
        this.main_img = img;
        this.main_img_name = img.getName();
    }

    public void setResBack(File img) {
        if (img == null)
            return;
        this.res_back = img;
        this.res_back_name = img.getName();
    }

    public void setResImg(ArrayList<ResManager> res) {
        this.res_img = res;
    }

    public void setResTxt(ArrayList<ResManager> res) {
        this.res_txt = res;
    }

    public void addResImg(ResManager res) {
        res_img.add(res);
    }

    public void addResTxt(ResManager res) {
        res_txt.add(res);
    }

    public File getMainImg() {
        return main_img;
    }

    public String getMainImgName() {
        return main_img.getName();
    }

    public int getPage() {
        return page;
    }

    public JSONArray getResCount() {
        return new JSONArray().put(res_count.first).put(res_count.second);
    }

    public File getResBack() {
        return res_back;
    }

    public String getResBackName() {
        return res_back != null ? res_back.getName() : null;
    }

    public ArrayList<ResManager> getResImg() {
        return res_img;
    }

    public ArrayList<ResManager> getResTxt() {
        return res_txt;
    }

    public ResManager getResImg(int i) {
        return res_img.get(i);
    }

    public ResManager getResTxt(int i) {
        return res_txt.get(i);
    }

    public JSONObject getImageData(int i) {
        try {
            ResManager res = res_img.get(i);
            JSONObject array = new JSONObject();
            array.put("main_img", res.getImgName());
            array.put("x", Math.round(res.getX()));
            array.put("y", Math.round(res.getY()));
            array.put("width", res.getWidth());
            array.put("height", res.getHeight());
            return array;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getImageSize() {
        return res_img.size();
    }

    public JSONObject getTextData(int i) {
        try {
            ResManager res = res_txt.get(i);
            JSONObject array = new JSONObject();
            array.put("text", res.getTxt());
            array.put("x", Math.round(res.getX()));
            array.put("y", Math.round(res.getY()));
            array.put("size", res.getSize());
            array.put("font", res.getFont());
            array.put("color", res.getColor());
            return array;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getTextSize() {
        return res_txt.size();
    }


}
