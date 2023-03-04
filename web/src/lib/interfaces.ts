import type {SvelteComponent} from "svelte";
import type {Icon} from "stwui";

export interface Module {
  name: String;
  title: String;
  path?: String;
  icon?: typeof Icon;
  component: typeof SvelteComponent;
}

export interface App {
  name: String;
  title: String;
  description: String;
  logo: typeof SvelteComponent
}

export interface SideBar {
  name?: String,
  items: Array<Module>
}

