package com.fms.springEx1.Controller;

import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fms.springEx1.Security.RoleService;
import com.fms.springEx1.Security.UserService;
import com.fms.springEx1.Security.Uuser;

@Controller
public class SecurityController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * 
	 * @param user
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String loginForm() {
		return "loginHtml";
	}

	@RequestMapping("/logout")
	public String logoutForm(HttpServletRequest request) throws ServletException {
		request.logout();
		return "redirect:login";
	}

//	@PostMapping("login/authenticate")
//	public String authenticate(@Valid Uuser user, BindingResult bindingResult, Model model) {
//		if (bindingResult.hasErrors()) {
//			return "loginHtml";
//		}
//		if (userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword()) == null) {
//			model.addAttribute("loginError", "error");
//			return "loginHtml";
//		}
//		return "redirect:/shop/orderResume";
//	}

	
	/**
	 * Méthode qui renvoit la liste des utilisateurs à l'administrateur
	 * @param model
	 * @param page
	 * @param size
	 * @param usename
	 * @param userId
	 * @return
	 */
	@RequestMapping("admin/users")
	public String users(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "usename", defaultValue = "") String usename,
			@RequestParam(name = "userId", defaultValue = "") Long userId) {

		if (userId != null) {
			userService.deleteById(userId);
		}
		Page<Uuser> users = userService.findByPageByPageAndUserName(usename, PageRequest.of(page, size));
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		model.addAttribute("pages", new int[users.getTotalPages()]);
		model.addAttribute("totalPages", users.getTotalPages());
		model.addAttribute("username", usename);
		model.addAttribute("listUsers", users.getContent());
		model.addAttribute("listOf", "List of users");
		return "users";
	}

	/**
	 * Méthode qui permet de mettre à jour le rôle d'un utilisateur
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping("admin/users/updateUserForm")
	public String updateUser(Model model, @RequestParam(name = "userId") Long userId) {
		model.addAttribute("uuser", userService.readById(userId));

		return "updateUser";
	}

	/**
	 * Méthode qui renvoit le formulaire d'ajout d'un utilisateur pour l'administrateur
	 * @param model
	 * @return
	 */
	@RequestMapping("admin/users/saveUserForm")
	public String saveUser(Model model) {
		model.addAttribute("uuser", new Uuser());

		return "saveNewUser";
	}

	/**
	 * Méthode qui permet d'ajouter un utilisateur avec un rôle
	 * @param uuser
	 * @param bindingResult
	 * @param userRole
	 * @param adminRole
	 * @param active
	 * @param model
	 * @return
	 */
	@PostMapping("admin/users/saveUser")
	public String saveUser(@Valid Uuser uuser, BindingResult bindingResult,
			@RequestParam(name = "userRole", defaultValue = "") String userRole,
			@RequestParam(name = "adminRole", defaultValue = "") String adminRole,
			@RequestParam("active") String active, Model model, RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			if (uuser.getUserId() != 0) {
				model.addAttribute("uuser", userService.readById(uuser.getUserId()));
				return "updateUser";
			} else
				return "saveNewUser";
		}
		if (active.equalsIgnoreCase("true"))
			uuser.setActive(true);
		else
			uuser.setActive(false);
		if (!userRole.equals(""))
			uuser.getRoles().add(roleService.getRoleByRoleName("USER"));
		if (!adminRole.equals(""))
			uuser.getRoles().add(roleService.getRoleByRoleName("ADMIN"));
		userService.saveUuser(uuser);
		return "redirect:/admin/users";
	}

	@GetMapping("registerUser")
	public String register(Model model) {
		model.addAttribute("user", new Uuser());
		return "register";
	}

	@PostMapping("registration")
	public String registration(@Valid Uuser user, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
//			model.addAttribute("user", user);
			return "redirect:registerUser";
		}
		user.setActive(true);
		user.getRoles().add(roleService.getRoleByRoleName("USER"));
		userService.saveUuser(user);
		return "redirect:/login";
	}
	
	/**
	 * Méthode qui renvoit le formulaire de changement de mot de passe
	 * @param model
	 * @param uuserId
	 * @return
	 */
	@GetMapping("changePasswordForm")
    public String updateProfile(Model model, @RequestParam(name = "uuserId", defaultValue = "") Long uuserId) {
		System.out.println("chane");
        model.addAttribute("uuser", userService.readById(uuserId));
//      model.addAttribute("uuser", new Uuser());
        return "passwordUpdate";
    }
	
	
	/**
	 * Méthode qui permet à l'utilisateur de changer de mot de passe
	 * @param model
	 * @param uuser
	 * @param bindingResult
	 * @param principal
	 * @param newPassword
	 * @param currentPassword
	 * @return
	 */
    @PostMapping("changePassword")
    public String changePassword(Model model, @Valid Uuser uuser, BindingResult bindingResult, Principal principal,
            @RequestParam("newPassword") String newPassword, @RequestParam("currentPassword") String currentPassword) {

        if (currentPassword.equals("") || newPassword.equals("")) {
            model.addAttribute("uuser", userService.readById(uuser.getUserId()));
            return "passwordUpdate";
        }
        
        if (!passwordEncoder.matches(currentPassword, userService.readById(uuser.getUserId()).getPassword())) {
            System.out.println("check db");
            model.addAttribute("uuser", userService.readById(uuser.getUserId()));
            model.addAttribute("errorPassword", "error");
            return "passwordUpdate";
        }
        //
        uuser.setPassword(passwordEncoder.encode(newPassword));
        uuser.setUserName(principal.getName());
        uuser.setActive(true);
        uuser.setRoles(userService.readById(uuser.getUserId()).getRoles());
        //update uuser with the new password
        userService.saveUuser(uuser);
        return "redirect:/logout";
    }


}
