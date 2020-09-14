package lk.customsProcessManagement.asset.chandlerLicense.controller;

import lk.customsProcessManagement.asset.chandlerLicense.entity.ChandlerLicense;
import lk.customsProcessManagement.asset.chandlerLicense.entity.Enum.LicenseStatus;
import lk.customsProcessManagement.asset.chandlerLicense.service.ChandlerLicenseService;
import lk.customsProcessManagement.util.interfaces.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping( "/chandlerLicense" )
public class ChandlerLicenseController implements AbstractController< ChandlerLicense, Integer > {
    private final ChandlerLicenseService chandlerLicenseService;

    public ChandlerLicenseController(ChandlerLicenseService chandlerLicenseService) {
        this.chandlerLicenseService = chandlerLicenseService;
    }

    private String commonLicense(Model model, List< ChandlerLicense > chandlerLicenses) {
        model.addAttribute("chandlerLicense", chandlerLicenses);
        return "chandlerLicense/chandlerLicense";
    }

    private String commonLicenseAdd(Model model, ChandlerLicense chandlerLicense, boolean addStatus) {
        model.addAttribute("chandlerLicense", chandlerLicense);
        model.addAttribute("addStatus", addStatus);
        return "chandlerLicense/addChandlerLicense";
    }

    @GetMapping
    public String findAll(Model model) {
        return commonLicense(model, chandlerLicenseService.findAll());
    }

    @GetMapping( "/valid" )
    public String validLicense(Model model) {
        return commonLicense(model, chandlerLicenseService.findByLicenseStatus(LicenseStatus.VALID));
    }

    @GetMapping( "/process" )
    public String processingLicense(Model model) {
        return commonLicense(model, chandlerLicenseService.findByLicenseStatus(LicenseStatus.PROCEED));
    }

    @GetMapping( "/invalid" )
    public String invalidLicense(Model model) {
        return commonLicense(model, chandlerLicenseService.findByLicenseStatus(LicenseStatus.INVALID));
    }

    @GetMapping( "/add" )
    public String addForm(Model model) {
        return commonLicenseAdd(model, new ChandlerLicense(), true);
    }

    @PostMapping( value = {"save", "update"} )
    public String persist(@Valid @ModelAttribute ChandlerLicense chandlerLicense, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes, Model model) {
        //todo=> all thing
        if(bindingResult.hasErrors()){
            return commonLicenseAdd(model, chandlerLicense, true);
        }
         return "redirect:/chandlerLicense";
    }

    @GetMapping( "/edit/{id}" )
    public String edit(@PathVariable Integer id, Model model) {
        return commonLicenseAdd(model, chandlerLicenseService.findById(id), true);
    }

    @GetMapping( "/delete/{id}" )
    public String delete(@PathVariable Integer id, Model model) {
        return "redirect:/chandlerLicense";
    }

    @GetMapping( "/view/{id}" )
    public String view(@PathVariable Integer id, Model model) {
        model.addAttribute("chandlerLicenseDetail", chandlerLicenseService.findById(id));
       return "chandlerLicense/chandlerLicense-detail";
    }
}