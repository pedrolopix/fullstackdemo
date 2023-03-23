<script lang="ts">
  import {authService} from "./AuthService";
  import {login} from "./AuthStore";
  import {CheckboxGroup, Input} from "stwui";
  import {minLength, required, useForm} from "svelte-use-form";


  let rememberMe: Boolean = !!($login.username || $login.password);

  const form = useForm({
    username: {initial: $login.username, validators: [required, minLength(1)]},
    password: {initial: $login.password, validators: [required, minLength(1)]},
    rememberMe: {initial: rememberMe ? "checked":""}
  });

  $: console.log($form.values);
  $: usernameError = !$form.username.valid ? "User name is not valid" : "";
  $: passwordError = !$form.username.valid ? "Password is required" : "";

  const doLogin = () => {
    if ($form.valid) {
      authService.doLogin($form.username.value, $form.password.value, $form.rememberMe.value === "checked")
    }
  }


</script>

<section class="h-screen">
  <div class="container h-full px-6 py-24 bg-neutral-100 ">
    <div
        class="g-6 flex h-full flex-wrap items-center justify-center lg:justify-between ">
      <div class="mb-12 md:mb-0 md:w-8/12 lg:w-6/12">
        <img
            src="https://tecdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg"
            class="w-full"
            alt="login image"/>
      </div>
      <div class="md:w-8/12 lg:ml-6 lg:w-5/12">
        <form use:form on:submit|preventDefault={doLogin}>
          <Input id="username" name="username" error="{usernameError}">
            <Input.Label slot="label">User name</Input.Label>
          </Input>
          <!-- Password input -->
          <Input id="password" name="password" showPasswordToggle>
            <Input.Label slot="label">Password</Input.Label>
          </Input>
          <div class="mb-6 flex items-center justify-between">
            <CheckboxGroup>
              <CheckboxGroup.Checkbox id="rememberMe" name="rememberMe">
                <CheckboxGroup.Checkbox.Label slot="label">Remember Me</CheckboxGroup.Checkbox.Label>
              </CheckboxGroup.Checkbox>
            </CheckboxGroup>
            <!--            <a-->
            <!--                href="#!"-->
            <!--                class="text-primary transition duration-150 ease-in-out hover:text-primary-600 focus:text-primary-600 active:text-primary-700 dark:text-primary-400 dark:hover:text-primary-500 dark:focus:text-primary-500 dark:active:text-primary-600"-->
            <!--            >Forgot password?</a-->
            <!--            >-->
          </div>
          <!-- Submit button -->
          <button
              type="submit"
              class="inline-block w-full rounded bg-primary px-7 pt-3 pb-2.5 text-sm font-medium uppercase leading-normal text-white shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:bg-primary-600 hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:bg-primary-600 focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:outline-none focus:ring-0 active:bg-primary-700 active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)]"
              data-te-ripple-init
              data-te-ripple-color="light">
            Sign in
          </button>

        </form>
      </div>
    </div>
  </div>
</section>
