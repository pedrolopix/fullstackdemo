import AppLayout from "./AppLayout.svelte";
import PublicPage from "./PublicPage.svelte";
import {dashboardModule, emptyModule, loginModule} from "./settings";


const userIsAdmin = () => true;

export const routes = [
  {
    name: '/',
    component: PublicPage,
    layout: AppLayout
  },
  {...loginModule},
  {...dashboardModule, layout: AppLayout},
  {...emptyModule, layout: AppLayout},
]
