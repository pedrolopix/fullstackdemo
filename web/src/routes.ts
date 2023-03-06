
import Login from "./auth/Login.svelte";
import Dashboard from "./app/Dashboard.svelte";
import AppLayout from "./AppLayout.svelte";
import PublicPage from "./PublicPage.svelte";
import EmptyPage from "./lib/EmptyPage.svelte";

const userIsAdmin = () => true;

export const routes = [
  {
    name: '/',
    component: PublicPage
  },
  {name: "login", component: Login},
  {name: "dashboard", component: Dashboard, layout: AppLayout},
  {name: "empty", component: EmptyPage, layout: AppLayout},
  {
    name: 'other',
    component: AppLayout,
    onlyIf: {guard: userIsAdmin, redirect: '/login'},
    nestedRoutes: [
      {name: 'index', component: Dashboard},
      {
        name: 'todo',
        component: '',
        nestedRoutes: [
          {name: '/', component: Dashboard},
          {name: 'show/:id', component: Dashboard},
        ],
      },
    ],
  },
]
