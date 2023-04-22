import type {App, MenuItem, Module} from "./lib/interfaces";
import {toMenu} from "./lib/interfaces";
import Logo from "./lib/Logo.svelte";
import Login from "./auth/Login.svelte";
import EmptyPage from "./lib/EmptyPage.svelte";
import DashboardPage from "./app/DashboardPage.svelte";
import {account_multiple, chart_box, file_document} from './lib/icons';


export let modules = new Map<String, Module>();

function add(module: Module) {
  modules.set(module.name, module);
  return module;
}

export const dashboardModule = add({name: "dashboard", href: "/dashboard", label: "Dashboard", component: DashboardPage, iconData: chart_box});
export const loginModule = add({name: "login", href: "/login", label: "Login", component: Login});
export const emptyModule = add({name: "empty", href: "/empty", label: "Empty Page", component: EmptyPage, iconData:file_document});

export const app: App = {
  name: 'myApp',
  title: 'myApp',
  description: 'Quarkus and Svelte fullstack demo',
  logo: Logo
}

export const sideBar: MenuItem[] = [toMenu(dashboardModule), toMenu(emptyModule)];
export const menuBar: MenuItem[] = [
  {
    name: 'team',
    iconData: account_multiple,
    label: 'Team',
    href: '#item2',
    children: [
      {
        name: 'sub1',
        label: 'Sub 1',
        href: '#item2-sub1'
      },
      {
        name: 'sub2',
        label: 'Sub 2',
        href: '#item2-sub2'
      }
    ]
  }
];

