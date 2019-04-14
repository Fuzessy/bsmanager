import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "./core/service/authentication.service";
import {UserService} from "./core/service/user.service";
import {ApplicationUser} from "./core/model/application-user";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'ng-app';
  isMenuToggleOn : boolean = true;

  menu = [
    {
      id: 1,
      icon: 'account_balance',
      label: 'Pénzügyek',
      open : false,
      subMenuItems: [
        {label: 'Tétel rögzítése', link:'addNewFinanceItem'},
        {label: 'Tételtípusok', link:'financeItemCategories'},
        {label: 'Számlatörténet', link:'financeHistory'},
        {label: 'Terv'},
        {label: 'Ismétlődő költségek'},
        {label: 'Számlatípusok'}
      ]
    },
    {
      id: 2,
      icon: 'shopping_cart',
      label: 'Bevásárlólista',
      open : false,
      subMenuItems:[
        {label: 'Új termék hozzáadása', link:'shoppingListItem'},
        {label: 'Bevásárlás', link:'shoppingList'},
        {label: 'Termékkatalógus'}
      ]
    },
    {
      id: 3,
      icon: 'store_mall_directory',
      label: 'Bodonyi',
      open : false,
      subMenuItems:[]
    },
    {
      id: 4,
      icon: 'build',
      label: 'Beállítások',
      open : false,
      subMenuItems:[]
    }
  ];
  private applicationUser: ApplicationUser;

  constructor( private authenticationService : AuthenticationService,
               private userService : UserService){

  }

  ngOnInit(){
    this.userService.getApplicationUser()
      .then( user => this.applicationUser = user);
  }

  authenticated(){
    return this.authenticationService.isAuthenticated();
  }

  logout(){
    this.authenticationService.logout();

  }

  toggleMenu() {
    this.isMenuToggleOn = !this.isMenuToggleOn;
  }


  toggleMenuItem(id : number) : void{
    let item = this.menu.find(e => e.id === id);
    item.open = !item.open;
  }

}
