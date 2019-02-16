package jabuhubtigermobile.jabu.jabuhubtigermobile.Interfaces;

public interface NetworkEvnts {
    void OnGetDataSuccess(String responseBody, Object object);
    void OnGetDataFailed(String ResponseBody, Object object,int status);
}