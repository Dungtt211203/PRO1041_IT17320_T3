/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ViewModels.QLSPVM;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface QLSPService {
    public String them(QLSPVM ql);

    public String sua(QLSPVM ql);

    public String xoa(QLSPVM ql);

    public List<QLSPVM> GetAll();
}
