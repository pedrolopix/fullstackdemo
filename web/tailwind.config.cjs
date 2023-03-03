module.exports = {
  content: [
    './src/**/*.{html,js,svelte,ts}',
    './node_modules/stwui/**/*.{svelte,js,ts,html}'
  ],
  plugins: [
    require('@tailwindcss/forms'),
    require('stwui/plugin')
  ],
  darkMode: 'class',
};
