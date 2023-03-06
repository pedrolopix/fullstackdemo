import type {SvelteComponent} from "svelte";

export interface App {
  name: string;
  title: string;
  description: string;
  logo: typeof SvelteComponent
}

export interface Module {
  name: string;
  label: string;
  href?: string;
  iconData?: string;
  component: typeof SvelteComponent;
}

export interface GroupMenuItem {
  name: string;
  label: string;
  href: string;
}

export interface MenuItem {
  name: string;
  iconData?: string;
  label: string;
  badge?: string;
  badgeType?: 'info' | 'warn' | 'error' | 'success';
  href: string;
  component: typeof SvelteComponent;
  children?: GroupMenuItem[];
}

export const toMenu = (module: Module): MenuItem => ({
  name: module.name,
  label: module.label,
  href: module.href,
  iconData: module.iconData,
  component: module.component
});
