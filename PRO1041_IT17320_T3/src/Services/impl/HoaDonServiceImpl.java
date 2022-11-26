/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.impl;

import DomainModel.HoaDon;
import Reponsitories.HoaDonRepository;
import Services.Hoa_DonService;
import ViewModels.HoaDonViewModel;
import java.util.ArrayList;

/**
 *
 * @author Kieu Oanh
 */
public class HoaDonServiceImpl implements Hoa_DonService {

    private HoaDonRepository nccRepo;
    public HoaDonServiceImpl(){
        this.nccRepo=new HoaDonRepository();
    }
    public void insert(HoaDon hd){
        this.nccRepo.add(hd);
    }
    public void update(HoaDon hd,String id){
        this.nccRepo.update(hd,id);
    }
    public void delete(String id){
        this.nccRepo.delete(id);
    }
    public ArrayList<HoaDonViewModel> getAll(){
        return this.nccRepo.GetAll();
    }
    
    public static void main(String[] args) {
        HoaDonServiceImpl hd = new HoaDonServiceImpl();
        for (HoaDonViewModel x : hd.getAll()) {
            x.toString();
        }
    }


 

   


   




    
}
