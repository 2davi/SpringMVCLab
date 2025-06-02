package kr.or.ddit.case03.view;

import java.util.Map;

import org.springframework.web.servlet.view.AbstractView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.MediaType;

public class GsonView extends AbstractView{

   
   public GsonView() {
      setContentType(MediaType.APPLICATION_JSON);
   }

   @Override
   protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
         HttpServletResponse response) throws Exception {
      // TODO Auto-generated method stub
      
   }

}
