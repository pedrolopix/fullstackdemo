<script lang="ts">
  import {Avatar, Button, Col, Divider, Drawer, Dropdown, Icon, Layout, Portal, Row, Swap, Toggle} from 'stwui';
  import {close, menu} from "stwui/icons";
  import {Route} from 'svelte-router-spa'
  import Navigation from "./lib/Navigation.svelte";
  import {app, sideBar} from "./settings";
  import Search from "./lib/Search.svelte";
  import {brightness_4, brightness_5} from "./lib/icons";
  import {authService} from "./auth/AuthService";

  let darkTheme = false;
  let openMenu = false;
  let userMenu = false;
  let params = {}
  export let currentRoute

  function handleOpenMenu() {
    openMenu = !openMenu;
  }

  function closeOpenMenu() {
    openMenu = false;
  }

  function toggleUserMenu() {
    userMenu = !userMenu;
  }

  function closeUserMenu() {
    userMenu = false;
  }

  $: if (darkTheme) {
    const htmlElement = document.documentElement;
    htmlElement.classList.add('dark');
  } else {
    const htmlElement = document.documentElement;
    htmlElement.classList.remove('dark');
  }
  const logout = () => {
    closeUserMenu();
    authService.logout();
  };

</script>

<svelte:head>
  {#if darkTheme}
    <meta name="theme-color" content="#242526"/>
  {:else}
    <meta name="theme-color" content="#ffffff"/>
  {/if}
  <title>Quarkus and Svelte</title>
  <meta name="description" content={app.description}/>
</svelte:head>
<div class="h-full w-full print:hidden">
  <Layout>
    <div
        class="fixed top-0 left-0 right-0 h-[var(--sat)] z-10 bg-light-surface dark:bg-dark-surface"
    />
    <Layout.Header class="shadow-none">
      <Button
          ariaLabel="open menu"
          shape="circle"
          class="inline-block lg:hidden mr-4 bg-light-icon-background text-light-icon dark:bg-dark-icon-background dark:text-dark-icon border-none outline-none"
          on:click={handleOpenMenu}
      >
        <Swap slot="icon">
          <Icon slot="on" data={menu} stroke="currentColor"/>
          <Icon slot="off" data={close} stroke="currentColor"/>
        </Swap>
      </Button>

      <a href="/">
        <svelte:component this={app.logo}/>
      </a>

      <div class="font-bold text-xl opacity-80 dark:opacity-100">{app.name}</div>

      <Layout.Header.Extra slot="extra">
        <Toggle name="toggle" bind:on={darkTheme}>
          <Toggle.LeftIcon slot="left-icon" data={brightness_5}/>
          <Toggle.RightIcon slot="right-icon" data={brightness_4}/>
        </Toggle>
        <Search/>
        <Dropdown bind:visible={userMenu}>
          <button
              aria-label="User menu"
              slot="trigger"
              on:click={toggleUserMenu}
              type="button"
              class="ml-4 h-10 w-10  text-sm flex rounded-full bg-light-icon-background dark:bg-dark-icon-background text-light-icon dark:text-dark-icon bg-center "
          >
            <Avatar src="broken-im11age.png"/>
          </button>
          <Dropdown.Items slot="items" placement="right" alignment="end">
            <Dropdown.Items.Item on:click={closeUserMenu} label="Preferences"/>
            <Dropdown.Items.Divider/>
            <Dropdown.Items.Item on:click={logout} label="Logout"/>
          </Dropdown.Items>
        </Dropdown>
      </Layout.Header.Extra>
    </Layout.Header>

    <Layout.Content
        class="h-[calc(100%-64px)]"
        expandedWidth="17rem"
    >
      <Layout.Content.Sidebar class="max-w-full bg-light-background dark:bg-dark-background">
        <Navigation items={sideBar}/>
      </Layout.Content.Sidebar>
      <Layout.Content.Body
          id="content-body"
          class="relative h-full w-full overflow-x-hidden overflow-y-auto pt-[calc(1rem+var(--sat))] pb-[calc(1rem+var(--sab))] pr-[calc(1rem+var(--sar))] pl-[calc(1rem+var(--sal))] md:pt-[calc(2rem+var(--sat))] md:pb-[calc(2rem+var(--sab))] md:pr-[calc(2rem+var(--sar))] md:pl-[calc(2rem+var(--sal))] bg-light-icon-background dark:bg-dark-icon-background text-light-icon dark:text-dark-icon"
      >
        <Row gutter="3" class="h-full w-full">
          <Col class="col-24 mb-4 ">
            <Route {currentRoute} {params}/>
            <Divider/>
          </Col>
          <slot/>
        </Row>
        v>
      </Layout.Content.Body>
    </Layout.Content>
  </Layout>
</div>

<Portal>
  {#if openMenu}
    <Drawer handleClose={closeOpenMenu} placement="left">
      <Drawer.Content class="overflow-y-auto p-4">
        <Navigation items={sideBar} handleClose={closeOpenMenu}/>
      </Drawer.Content>
    </Drawer>
  {/if}
</Portal>
