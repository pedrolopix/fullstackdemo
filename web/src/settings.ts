import type {App, Module, SideBar} from "./lib/interfaces";
import Logo from "./lib/Logo.svelte";
import Login from "./auth/Login.svelte";
import {Dashboard} from "radix-icons-svelte";
import EmptyPage from "./lib/EmptyPage.svelte";

const dashboard = {name: "dashboard", path: "/dashboard", title: "Dashboard", component: Dashboard};
const login = {name: "login", path: "login", title: "Login", component: Login};
const empty = {name: "empty", path: "/empty", title: "Empty Page", component: EmptyPage};
export const modules: Array<Module> = [
  login,
  dashboard,
  login
]

export const app: App = {
  name: 'myApp',
  title: 'myApp',
  description: 'Quarkus and Stelve fullstack demo',
  logo: Logo
}

export const sideBar: SideBar = {
  items: [dashboard, empty]
}
