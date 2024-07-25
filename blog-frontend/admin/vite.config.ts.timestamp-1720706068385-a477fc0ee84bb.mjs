// vite.config.ts
import { resolve } from "node:path";
import { fileURLToPath } from "node:url";
import * as process from "node:process";
import { loadEnv } from "file:///E:/study/project/blog/blog/blog-frontend/admin/node_modules/.pnpm/vite@4.5.0_@types+node@20.9.0_less@4.2.0_sass@1.69.5/node_modules/vite/dist/node/index.js";

// plugins/index.ts
import vue from "file:///E:/study/project/blog/blog/blog-frontend/admin/node_modules/.pnpm/@vitejs+plugin-vue@4.4.1_vite@4.5.0_vue@3.3.8/node_modules/@vitejs/plugin-vue/dist/index.mjs";
import vueJsx from "file:///E:/study/project/blog/blog/blog-frontend/admin/node_modules/.pnpm/@vitejs+plugin-vue-jsx@3.0.2_vite@4.5.0_vue@3.3.8/node_modules/@vitejs/plugin-vue-jsx/dist/index.mjs";
import AutoImport from "file:///E:/study/project/blog/blog/blog-frontend/admin/node_modules/.pnpm/unplugin-auto-import@0.16.7_@vueuse+core@10.6.0/node_modules/unplugin-auto-import/dist/vite.js";
import GenerateConfig from "file:///E:/study/project/blog/blog/blog-frontend/admin/node_modules/.pnpm/unplugin-config@0.1.3_esbuild@0.19.5_vite@4.5.0/node_modules/unplugin-config/dist/vite.js";
import Components from "file:///E:/study/project/blog/blog/blog-frontend/admin/node_modules/.pnpm/unplugin-vue-components@0.25.2_vue@3.3.8/node_modules/unplugin-vue-components/dist/vite.mjs";
import VitePluginPreloadAll from "file:///E:/study/project/blog/blog/blog-frontend/admin/node_modules/.pnpm/vite-plugin-preload@0.3.1_vite@4.5.0/node_modules/vite-plugin-preload/dist/index.mjs";
import Unocss from "file:///E:/study/project/blog/blog/blog-frontend/admin/node_modules/.pnpm/unocss@0.56.5_postcss@8.4.38_vite@4.5.0/node_modules/unocss/dist/vite.mjs";
import AntdvResolver from "file:///E:/study/project/blog/blog/blog-frontend/admin/node_modules/.pnpm/antdv-component-resolver@1.0.4_unplugin-vue-components@0.25.2/node_modules/antdv-component-resolver/dist/index.mjs";

// plugins/constants.ts
var GLOB_CONFIG_FILE_NAME = "_app.config.js";
var OUTPUT_DIR = "dist";

// plugins/vite-build-info.ts
import { readdir, stat } from "node:fs";
import dayjs from "file:///E:/study/project/blog/blog/blog-frontend/admin/node_modules/.pnpm/dayjs@1.11.10/node_modules/dayjs/dayjs.min.js";
import duration from "file:///E:/study/project/blog/blog/blog-frontend/admin/node_modules/.pnpm/dayjs@1.11.10/node_modules/dayjs/plugin/duration.js";
import pkg from "file:///E:/study/project/blog/blog/blog-frontend/admin/node_modules/.pnpm/picocolors@1.0.0/node_modules/picocolors/picocolors.js";
var { green, blue, bold } = pkg;
dayjs.extend(duration);
var fileListTotal = [];
function recursiveDirectory(folder, callback) {
  readdir(folder, (err, files) => {
    if (err)
      throw err;
    let count = 0;
    const checkEnd = () => {
      ++count === files.length && callback();
    };
    files.forEach((item) => {
      stat(`${folder}/${item}`, async (err2, stats) => {
        if (err2)
          throw err2;
        if (stats.isFile()) {
          fileListTotal.push(stats.size);
          checkEnd();
        } else if (stats.isDirectory()) {
          recursiveDirectory(`${folder}/${item}/`, checkEnd);
        }
      });
    });
    files.length === 0 && callback();
  });
}
function sum(arr) {
  return arr.reduce((t, c) => {
    return t + c;
  }, 0);
}
function formatBytes(a, b) {
  if (a === 0)
    return "0 Bytes";
  const c = 1024;
  const d = b || 2;
  const e = ["Bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"];
  const f = Math.floor(Math.log(a) / Math.log(c));
  return `${Number.parseFloat((a / c ** f).toFixed(d))} ${e[f]}`;
}
function viteBuildInfo(name) {
  let config;
  let startTime;
  let endTime;
  return {
    name: "vite:buildInfo",
    configResolved(resolvedConfig) {
      config = resolvedConfig;
    },
    buildStart() {
      console.log(
        bold(
          green(
            `\u{1F44F}\u6B22\u8FCE\u4F7F\u7528${blue(`[${name}]`)}\uFF0C\u73B0\u5728\u6B63\u5168\u529B\u4E3A\u60A8${config.command === "build" ? "\u6253\u5305" : "\u7F16\u8BD1"}`
          )
        )
      );
      if (config.command === "build")
        startTime = dayjs(/* @__PURE__ */ new Date());
    },
    closeBundle() {
      if (config.command === "build") {
        endTime = dayjs(/* @__PURE__ */ new Date());
        recursiveDirectory(config.build.outDir, () => {
          console.log(
            bold(
              green(
                `\u606D\u559C\u6253\u5305\u5B8C\u6210\u{1F389}\uFF08\u603B\u7528\u65F6${dayjs.duration(endTime.diff(startTime)).format("mm\u5206ss\u79D2")}\uFF0C\u6253\u5305\u540E\u7684\u5927\u5C0F\u4E3A${formatBytes(
                  sum(fileListTotal)
                )}\uFF09`
              )
            )
          );
        });
      }
    }
  };
}

// plugins/index.ts
function createVitePlugins(env) {
  const vitePluginList = [
    vue(),
    vueJsx(),
    VitePluginPreloadAll(),
    AutoImport({
      imports: [
        "vue",
        "vue-router",
        "vue-i18n",
        "@vueuse/core",
        "pinia"
      ],
      dts: "types/auto-imports.d.ts",
      dirs: ["src/stores", "src/composables"]
    }),
    Components({
      resolvers: [AntdvResolver()],
      dts: "types/components.d.ts",
      dirs: ["src/components"]
    }),
    // https://github.com/kirklin/unplugin-config
    GenerateConfig({
      appName: env.VITE_GLOB_APP_TITLE,
      configFile: {
        generate: true,
        fileName: GLOB_CONFIG_FILE_NAME,
        outputDir: OUTPUT_DIR
      },
      envVariables: {
        prefix: "VITE_GLOB_"
      }
    }),
    Unocss(),
    viteBuildInfo(env.VITE_APP_NAME)
  ];
  return vitePluginList;
}

// vite.config.ts
var __vite_injected_original_import_meta_url = "file:///E:/study/project/blog/blog/blog-frontend/admin/vite.config.ts";
var baseSrc = fileURLToPath(new URL("./src", __vite_injected_original_import_meta_url));
var vite_config_default = ({ mode }) => {
  const env = loadEnv(mode, process.cwd());
  const proxyObj = {};
  if (mode === "development" && env.VITE_APP_BASE_API_DEV && env.VITE_APP_BASE_URL_DEV) {
    proxyObj[env.VITE_APP_BASE_API_DEV] = {
      target: env.VITE_APP_BASE_URL_DEV,
      changeOrigin: true,
      rewrite: (path) => path.replace(new RegExp(`^${env.VITE_APP_BASE_API_DEV}`), "")
    };
  }
  return {
    plugins: createVitePlugins(env),
    resolve: {
      alias: [
        {
          find: "dayjs",
          replacement: "dayjs/esm"
        },
        {
          find: /^dayjs\/locale/,
          replacement: "dayjs/esm/locale"
        },
        {
          find: /^dayjs\/plugin/,
          replacement: "dayjs/esm/plugin"
        },
        {
          find: "vue-i18n",
          replacement: mode === "development" ? "vue-i18n/dist/vue-i18n.esm-browser.js" : "vue-i18n/dist/vue-i18n.esm-bundler.js"
        },
        {
          find: /^ant-design-vue\/es$/,
          replacement: "ant-design-vue/es"
        },
        {
          find: /^ant-design-vue\/dist$/,
          replacement: "ant-design-vue/dist"
        },
        {
          find: /^ant-design-vue\/lib$/,
          replacement: "ant-design-vue/es"
        },
        {
          find: /^ant-design-vue$/,
          replacement: "ant-design-vue/es"
        },
        {
          find: "lodash",
          replacement: "lodash-es"
        },
        {
          find: "~@",
          replacement: baseSrc
        },
        {
          find: "~",
          replacement: baseSrc
        },
        {
          find: "@",
          replacement: baseSrc
        },
        {
          find: "~#",
          replacement: resolve(baseSrc, "./enums")
        }
      ]
    },
    build: {
      chunkSizeWarningLimit: 4096,
      outDir: OUTPUT_DIR,
      rollupOptions: {
        output: {
          manualChunks: {
            vue: ["vue", "vue-router", "pinia", "vue-i18n", "@vueuse/core"],
            antd: ["ant-design-vue", "@ant-design/icons-vue", "dayjs"]
            // lodash: ['loadsh-es'],
          }
        }
      }
    },
    server: {
      port: 6678,
      host: "0.0.0.0",
      proxy: {
        ...proxyObj,
        [env.VITE_APP_BASE_API]: {
          target: env.VITE_APP_BASE_URL,
          changeOrigin: true,
          rewrite: (path) => path.replace(new RegExp(`^${env.VITE_APP_BASE_API}`), "")
        }
      }
    },
    test: {
      globals: true,
      environment: "jsdom"
    }
  };
};
export {
  vite_config_default as default
};
//# sourceMappingURL=data:application/json;base64,ewogICJ2ZXJzaW9uIjogMywKICAic291cmNlcyI6IFsidml0ZS5jb25maWcudHMiLCAicGx1Z2lucy9pbmRleC50cyIsICJwbHVnaW5zL2NvbnN0YW50cy50cyIsICJwbHVnaW5zL3ZpdGUtYnVpbGQtaW5mby50cyJdLAogICJzb3VyY2VzQ29udGVudCI6IFsiY29uc3QgX192aXRlX2luamVjdGVkX29yaWdpbmFsX2Rpcm5hbWUgPSBcIkU6XFxcXHN0dWR5XFxcXHByb2plY3RcXFxcYmxvZ1xcXFxibG9nXFxcXGJsb2ctZnJvbnRlbmRcXFxcYWRtaW5cIjtjb25zdCBfX3ZpdGVfaW5qZWN0ZWRfb3JpZ2luYWxfZmlsZW5hbWUgPSBcIkU6XFxcXHN0dWR5XFxcXHByb2plY3RcXFxcYmxvZ1xcXFxibG9nXFxcXGJsb2ctZnJvbnRlbmRcXFxcYWRtaW5cXFxcdml0ZS5jb25maWcudHNcIjtjb25zdCBfX3ZpdGVfaW5qZWN0ZWRfb3JpZ2luYWxfaW1wb3J0X21ldGFfdXJsID0gXCJmaWxlOi8vL0U6L3N0dWR5L3Byb2plY3QvYmxvZy9ibG9nL2Jsb2ctZnJvbnRlbmQvYWRtaW4vdml0ZS5jb25maWcudHNcIjsvLy8gPHJlZmVyZW5jZSB0eXBlcz1cInZpdGVzdFwiIC8+XHJcbmltcG9ydCB7IHJlc29sdmUgfSBmcm9tICdub2RlOnBhdGgnXHJcbmltcG9ydCB7IGZpbGVVUkxUb1BhdGggfSBmcm9tICdub2RlOnVybCdcclxuaW1wb3J0ICogYXMgcHJvY2VzcyBmcm9tICdub2RlOnByb2Nlc3MnXHJcbmltcG9ydCB7IGxvYWRFbnYgfSBmcm9tICd2aXRlJ1xyXG5pbXBvcnQgdHlwZSB7IENvbmZpZ0VudiwgVXNlckNvbmZpZyB9IGZyb20gJ3ZpdGUnXHJcbmltcG9ydCB7IGNyZWF0ZVZpdGVQbHVnaW5zIH0gZnJvbSAnLi9wbHVnaW5zJ1xyXG5pbXBvcnQgeyBPVVRQVVRfRElSIH0gZnJvbSAnLi9wbHVnaW5zL2NvbnN0YW50cydcclxuXHJcbmNvbnN0IGJhc2VTcmMgPSBmaWxlVVJMVG9QYXRoKG5ldyBVUkwoJy4vc3JjJywgaW1wb3J0Lm1ldGEudXJsKSlcclxuLy8gaHR0cHM6Ly92aXRlanMuZGV2L2NvbmZpZy9cclxuZXhwb3J0IGRlZmF1bHQgKHsgbW9kZSB9OiBDb25maWdFbnYpOiBVc2VyQ29uZmlnID0+IHtcclxuICBjb25zdCBlbnYgPSBsb2FkRW52KG1vZGUsIHByb2Nlc3MuY3dkKCkpXHJcbiAgY29uc3QgcHJveHlPYmogPSB7fVxyXG4gIGlmIChtb2RlID09PSAnZGV2ZWxvcG1lbnQnICYmIGVudi5WSVRFX0FQUF9CQVNFX0FQSV9ERVYgJiYgZW52LlZJVEVfQVBQX0JBU0VfVVJMX0RFVikge1xyXG4gICAgcHJveHlPYmpbZW52LlZJVEVfQVBQX0JBU0VfQVBJX0RFVl0gPSB7XHJcbiAgICAgIHRhcmdldDogZW52LlZJVEVfQVBQX0JBU0VfVVJMX0RFVixcclxuICAgICAgY2hhbmdlT3JpZ2luOiB0cnVlLFxyXG4gICAgICByZXdyaXRlOiBwYXRoID0+IHBhdGgucmVwbGFjZShuZXcgUmVnRXhwKGBeJHtlbnYuVklURV9BUFBfQkFTRV9BUElfREVWfWApLCAnJyksXHJcbiAgICB9XHJcbiAgfVxyXG4gIHJldHVybiB7XHJcbiAgICBwbHVnaW5zOiBjcmVhdGVWaXRlUGx1Z2lucyhlbnYpLFxyXG4gICAgcmVzb2x2ZToge1xyXG4gICAgICBhbGlhczogW1xyXG4gICAgICAgIHtcclxuICAgICAgICAgIGZpbmQ6ICdkYXlqcycsXHJcbiAgICAgICAgICByZXBsYWNlbWVudDogJ2RheWpzL2VzbScsXHJcbiAgICAgICAgfSxcclxuICAgICAgICB7XHJcbiAgICAgICAgICBmaW5kOiAvXmRheWpzXFwvbG9jYWxlLyxcclxuICAgICAgICAgIHJlcGxhY2VtZW50OiAnZGF5anMvZXNtL2xvY2FsZScsXHJcbiAgICAgICAgfSxcclxuICAgICAgICB7XHJcbiAgICAgICAgICBmaW5kOiAvXmRheWpzXFwvcGx1Z2luLyxcclxuICAgICAgICAgIHJlcGxhY2VtZW50OiAnZGF5anMvZXNtL3BsdWdpbicsXHJcbiAgICAgICAgfSxcclxuICAgICAgICB7XHJcbiAgICAgICAgICBmaW5kOiAndnVlLWkxOG4nLFxyXG4gICAgICAgICAgcmVwbGFjZW1lbnQ6IG1vZGUgPT09ICdkZXZlbG9wbWVudCcgPyAndnVlLWkxOG4vZGlzdC92dWUtaTE4bi5lc20tYnJvd3Nlci5qcycgOiAndnVlLWkxOG4vZGlzdC92dWUtaTE4bi5lc20tYnVuZGxlci5qcycsXHJcbiAgICAgICAgfSxcclxuICAgICAgICB7XHJcbiAgICAgICAgICBmaW5kOiAvXmFudC1kZXNpZ24tdnVlXFwvZXMkLyxcclxuICAgICAgICAgIHJlcGxhY2VtZW50OiAnYW50LWRlc2lnbi12dWUvZXMnLFxyXG4gICAgICAgIH0sXHJcbiAgICAgICAge1xyXG4gICAgICAgICAgZmluZDogL15hbnQtZGVzaWduLXZ1ZVxcL2Rpc3QkLyxcclxuICAgICAgICAgIHJlcGxhY2VtZW50OiAnYW50LWRlc2lnbi12dWUvZGlzdCcsXHJcbiAgICAgICAgfSxcclxuICAgICAgICB7XHJcbiAgICAgICAgICBmaW5kOiAvXmFudC1kZXNpZ24tdnVlXFwvbGliJC8sXHJcbiAgICAgICAgICByZXBsYWNlbWVudDogJ2FudC1kZXNpZ24tdnVlL2VzJyxcclxuICAgICAgICB9LFxyXG4gICAgICAgIHtcclxuICAgICAgICAgIGZpbmQ6IC9eYW50LWRlc2lnbi12dWUkLyxcclxuICAgICAgICAgIHJlcGxhY2VtZW50OiAnYW50LWRlc2lnbi12dWUvZXMnLFxyXG4gICAgICAgIH0sXHJcbiAgICAgICAge1xyXG4gICAgICAgICAgZmluZDogJ2xvZGFzaCcsXHJcbiAgICAgICAgICByZXBsYWNlbWVudDogJ2xvZGFzaC1lcycsXHJcbiAgICAgICAgfSxcclxuICAgICAgICB7XHJcbiAgICAgICAgICBmaW5kOiAnfkAnLFxyXG4gICAgICAgICAgcmVwbGFjZW1lbnQ6IGJhc2VTcmMsXHJcbiAgICAgICAgfSxcclxuICAgICAgICB7XHJcbiAgICAgICAgICBmaW5kOiAnficsXHJcbiAgICAgICAgICByZXBsYWNlbWVudDogYmFzZVNyYyxcclxuICAgICAgICB9LFxyXG4gICAgICAgIHtcclxuICAgICAgICAgIGZpbmQ6ICdAJyxcclxuICAgICAgICAgIHJlcGxhY2VtZW50OiBiYXNlU3JjLFxyXG4gICAgICAgIH0sXHJcbiAgICAgICAge1xyXG4gICAgICAgICAgZmluZDogJ34jJyxcclxuICAgICAgICAgIHJlcGxhY2VtZW50OiByZXNvbHZlKGJhc2VTcmMsICcuL2VudW1zJyksXHJcbiAgICAgICAgfSxcclxuICAgICAgXSxcclxuICAgIH0sXHJcbiAgICBidWlsZDoge1xyXG4gICAgICBjaHVua1NpemVXYXJuaW5nTGltaXQ6IDQwOTYsXHJcbiAgICAgIG91dERpcjogT1VUUFVUX0RJUixcclxuICAgICAgcm9sbHVwT3B0aW9uczoge1xyXG4gICAgICAgIG91dHB1dDoge1xyXG4gICAgICAgICAgbWFudWFsQ2h1bmtzOiB7XHJcbiAgICAgICAgICAgIHZ1ZTogWyd2dWUnLCAndnVlLXJvdXRlcicsICdwaW5pYScsICd2dWUtaTE4bicsICdAdnVldXNlL2NvcmUnXSxcclxuICAgICAgICAgICAgYW50ZDogWydhbnQtZGVzaWduLXZ1ZScsICdAYW50LWRlc2lnbi9pY29ucy12dWUnLCAnZGF5anMnXSxcclxuICAgICAgICAgICAgLy8gbG9kYXNoOiBbJ2xvYWRzaC1lcyddLFxyXG4gICAgICAgICAgfSxcclxuICAgICAgICB9LFxyXG4gICAgICB9LFxyXG4gICAgfSxcclxuICAgIHNlcnZlcjoge1xyXG4gICAgICBwb3J0OiA2Njc4LFxyXG4gICAgICBob3N0OiAnMC4wLjAuMCcsXHJcbiAgICAgIHByb3h5OiB7XHJcbiAgICAgICAgLi4ucHJveHlPYmosXHJcbiAgICAgICAgW2Vudi5WSVRFX0FQUF9CQVNFX0FQSV06IHtcclxuICAgICAgICAgIHRhcmdldDogZW52LlZJVEVfQVBQX0JBU0VfVVJMLFxyXG4gICAgICAgICAgY2hhbmdlT3JpZ2luOiB0cnVlLFxyXG4gICAgICAgICAgcmV3cml0ZTogcGF0aCA9PiBwYXRoLnJlcGxhY2UobmV3IFJlZ0V4cChgXiR7ZW52LlZJVEVfQVBQX0JBU0VfQVBJfWApLCAnJyksXHJcbiAgICAgICAgfSxcclxuICAgICAgfSxcclxuICAgIH0sXHJcbiAgICB0ZXN0OiB7XHJcbiAgICAgIGdsb2JhbHM6IHRydWUsXHJcbiAgICAgIGVudmlyb25tZW50OiAnanNkb20nLFxyXG4gICAgfSxcclxuICB9XHJcbn1cclxuIiwgImNvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9kaXJuYW1lID0gXCJFOlxcXFxzdHVkeVxcXFxwcm9qZWN0XFxcXGJsb2dcXFxcYmxvZ1xcXFxibG9nLWZyb250ZW5kXFxcXGFkbWluXFxcXHBsdWdpbnNcIjtjb25zdCBfX3ZpdGVfaW5qZWN0ZWRfb3JpZ2luYWxfZmlsZW5hbWUgPSBcIkU6XFxcXHN0dWR5XFxcXHByb2plY3RcXFxcYmxvZ1xcXFxibG9nXFxcXGJsb2ctZnJvbnRlbmRcXFxcYWRtaW5cXFxccGx1Z2luc1xcXFxpbmRleC50c1wiO2NvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9pbXBvcnRfbWV0YV91cmwgPSBcImZpbGU6Ly8vRTovc3R1ZHkvcHJvamVjdC9ibG9nL2Jsb2cvYmxvZy1mcm9udGVuZC9hZG1pbi9wbHVnaW5zL2luZGV4LnRzXCI7aW1wb3J0IHR5cGUgeyBQbHVnaW5PcHRpb24gfSBmcm9tICd2aXRlJ1xyXG5pbXBvcnQgdnVlIGZyb20gJ0B2aXRlanMvcGx1Z2luLXZ1ZSdcclxuaW1wb3J0IHZ1ZUpzeCBmcm9tICdAdml0ZWpzL3BsdWdpbi12dWUtanN4J1xyXG5pbXBvcnQgQXV0b0ltcG9ydCBmcm9tICd1bnBsdWdpbi1hdXRvLWltcG9ydC92aXRlJ1xyXG5pbXBvcnQgR2VuZXJhdGVDb25maWcgZnJvbSAndW5wbHVnaW4tY29uZmlnL3ZpdGUnXHJcbmltcG9ydCBDb21wb25lbnRzIGZyb20gJ3VucGx1Z2luLXZ1ZS1jb21wb25lbnRzL3ZpdGUnXHJcbmltcG9ydCBWaXRlUGx1Z2luUHJlbG9hZEFsbCBmcm9tICd2aXRlLXBsdWdpbi1wcmVsb2FkJ1xyXG5pbXBvcnQgVW5vY3NzIGZyb20gJ3Vub2Nzcy92aXRlJ1xyXG5pbXBvcnQgQW50ZHZSZXNvbHZlciBmcm9tICdhbnRkdi1jb21wb25lbnQtcmVzb2x2ZXInXHJcbmltcG9ydCB7IEdMT0JfQ09ORklHX0ZJTEVfTkFNRSwgT1VUUFVUX0RJUiB9IGZyb20gJy4vY29uc3RhbnRzJ1xyXG5pbXBvcnQgeyB2aXRlQnVpbGRJbmZvIH0gZnJvbSAnLi92aXRlLWJ1aWxkLWluZm8nXHJcblxyXG5leHBvcnQgZnVuY3Rpb24gY3JlYXRlVml0ZVBsdWdpbnMoZW52OiBSZWNvcmQ8c3RyaW5nLCBzdHJpbmc+KSB7XHJcbiAgY29uc3Qgdml0ZVBsdWdpbkxpc3Q6IChQbHVnaW5PcHRpb24gfCBQbHVnaW5PcHRpb25bXSlbXSA9IFtcclxuICAgIHZ1ZSgpLFxyXG4gICAgdnVlSnN4KCksXHJcbiAgICBWaXRlUGx1Z2luUHJlbG9hZEFsbCgpLFxyXG4gICAgQXV0b0ltcG9ydCh7XHJcbiAgICAgIGltcG9ydHM6IFtcclxuICAgICAgICAndnVlJyxcclxuICAgICAgICAndnVlLXJvdXRlcicsXHJcbiAgICAgICAgJ3Z1ZS1pMThuJyxcclxuICAgICAgICAnQHZ1ZXVzZS9jb3JlJyxcclxuICAgICAgICAncGluaWEnLFxyXG4gICAgICBdLFxyXG4gICAgICBkdHM6ICd0eXBlcy9hdXRvLWltcG9ydHMuZC50cycsXHJcbiAgICAgIGRpcnM6IFsnc3JjL3N0b3JlcycsICdzcmMvY29tcG9zYWJsZXMnXSxcclxuICAgIH0pLFxyXG4gICAgQ29tcG9uZW50cyh7XHJcbiAgICAgIHJlc29sdmVyczogW0FudGR2UmVzb2x2ZXIoKV0sXHJcbiAgICAgIGR0czogJ3R5cGVzL2NvbXBvbmVudHMuZC50cycsXHJcbiAgICAgIGRpcnM6IFsnc3JjL2NvbXBvbmVudHMnXSxcclxuICAgIH0pLFxyXG4gICAgLy8gaHR0cHM6Ly9naXRodWIuY29tL2tpcmtsaW4vdW5wbHVnaW4tY29uZmlnXHJcbiAgICBHZW5lcmF0ZUNvbmZpZyh7XHJcbiAgICAgIGFwcE5hbWU6IGVudi5WSVRFX0dMT0JfQVBQX1RJVExFLFxyXG4gICAgICBjb25maWdGaWxlOiB7XHJcbiAgICAgICAgZ2VuZXJhdGU6IHRydWUsXHJcbiAgICAgICAgZmlsZU5hbWU6IEdMT0JfQ09ORklHX0ZJTEVfTkFNRSxcclxuICAgICAgICBvdXRwdXREaXI6IE9VVFBVVF9ESVIsXHJcbiAgICAgIH0sXHJcbiAgICAgIGVudlZhcmlhYmxlczoge1xyXG4gICAgICAgIHByZWZpeDogJ1ZJVEVfR0xPQl8nLFxyXG4gICAgICB9LFxyXG4gICAgfSksXHJcbiAgICBVbm9jc3MoKSxcclxuICAgIHZpdGVCdWlsZEluZm8oZW52LlZJVEVfQVBQX05BTUUpLFxyXG4gIF1cclxuICByZXR1cm4gdml0ZVBsdWdpbkxpc3RcclxufVxyXG4iLCAiY29uc3QgX192aXRlX2luamVjdGVkX29yaWdpbmFsX2Rpcm5hbWUgPSBcIkU6XFxcXHN0dWR5XFxcXHByb2plY3RcXFxcYmxvZ1xcXFxibG9nXFxcXGJsb2ctZnJvbnRlbmRcXFxcYWRtaW5cXFxccGx1Z2luc1wiO2NvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9maWxlbmFtZSA9IFwiRTpcXFxcc3R1ZHlcXFxccHJvamVjdFxcXFxibG9nXFxcXGJsb2dcXFxcYmxvZy1mcm9udGVuZFxcXFxhZG1pblxcXFxwbHVnaW5zXFxcXGNvbnN0YW50cy50c1wiO2NvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9pbXBvcnRfbWV0YV91cmwgPSBcImZpbGU6Ly8vRTovc3R1ZHkvcHJvamVjdC9ibG9nL2Jsb2cvYmxvZy1mcm9udGVuZC9hZG1pbi9wbHVnaW5zL2NvbnN0YW50cy50c1wiOy8vIFRoaXMgY29uc3RhbnQgZGVmaW5lcyB0aGUgbmFtZSBvZiB0aGUgY29uZmlndXJhdGlvbiBmaWxlIHRoYXQgd2lsbCBiZSB1c2VkIGluIHRoZSBwcm9kdWN0aW9uIGVudmlyb25tZW50XHJcbmV4cG9ydCBjb25zdCBHTE9CX0NPTkZJR19GSUxFX05BTUUgPSAnX2FwcC5jb25maWcuanMnXHJcblxyXG4vLyBUaGlzIGNvbnN0YW50IHNldHMgdGhlIG91dHB1dCBkaXJlY3RvcnkgZm9yIHRoZSBWaXRlIHBhY2thZ2VcclxuZXhwb3J0IGNvbnN0IE9VVFBVVF9ESVIgPSAnZGlzdCdcclxuIiwgImNvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9kaXJuYW1lID0gXCJFOlxcXFxzdHVkeVxcXFxwcm9qZWN0XFxcXGJsb2dcXFxcYmxvZ1xcXFxibG9nLWZyb250ZW5kXFxcXGFkbWluXFxcXHBsdWdpbnNcIjtjb25zdCBfX3ZpdGVfaW5qZWN0ZWRfb3JpZ2luYWxfZmlsZW5hbWUgPSBcIkU6XFxcXHN0dWR5XFxcXHByb2plY3RcXFxcYmxvZ1xcXFxibG9nXFxcXGJsb2ctZnJvbnRlbmRcXFxcYWRtaW5cXFxccGx1Z2luc1xcXFx2aXRlLWJ1aWxkLWluZm8udHNcIjtjb25zdCBfX3ZpdGVfaW5qZWN0ZWRfb3JpZ2luYWxfaW1wb3J0X21ldGFfdXJsID0gXCJmaWxlOi8vL0U6L3N0dWR5L3Byb2plY3QvYmxvZy9ibG9nL2Jsb2ctZnJvbnRlbmQvYWRtaW4vcGx1Z2lucy92aXRlLWJ1aWxkLWluZm8udHNcIjtpbXBvcnQgeyByZWFkZGlyLCBzdGF0IH0gZnJvbSAnbm9kZTpmcydcclxuaW1wb3J0IHR5cGUgeyBQbHVnaW4sIFJlc29sdmVkQ29uZmlnIH0gZnJvbSAndml0ZSdcclxuaW1wb3J0IGRheWpzIGZyb20gJ2RheWpzJ1xyXG5pbXBvcnQgdHlwZSB7IERheWpzIH0gZnJvbSAnZGF5anMnXHJcbmltcG9ydCBkdXJhdGlvbiBmcm9tICdkYXlqcy9wbHVnaW4vZHVyYXRpb24nXHJcbmltcG9ydCBwa2cgZnJvbSAncGljb2NvbG9ycydcclxuXHJcbmNvbnN0IHsgZ3JlZW4sIGJsdWUsIGJvbGQgfSA9IHBrZ1xyXG5kYXlqcy5leHRlbmQoZHVyYXRpb24pXHJcblxyXG5jb25zdCBmaWxlTGlzdFRvdGFsOiBudW1iZXJbXSA9IFtdXHJcblxyXG5mdW5jdGlvbiByZWN1cnNpdmVEaXJlY3RvcnkoZm9sZGVyOiBzdHJpbmcsIGNhbGxiYWNrOiBGdW5jdGlvbik6IHZvaWQge1xyXG4gIHJlYWRkaXIoZm9sZGVyLCAoZXJyLCBmaWxlczogc3RyaW5nW10pID0+IHtcclxuICAgIGlmIChlcnIpXHJcbiAgICAgIHRocm93IGVyclxyXG4gICAgbGV0IGNvdW50ID0gMFxyXG4gICAgY29uc3QgY2hlY2tFbmQgPSAoKSA9PiB7XHJcbiAgICAgICsrY291bnQgPT09IGZpbGVzLmxlbmd0aCAmJiBjYWxsYmFjaygpXHJcbiAgICB9XHJcbiAgICBmaWxlcy5mb3JFYWNoKChpdGVtOiBzdHJpbmcpID0+IHtcclxuICAgICAgc3RhdChgJHtmb2xkZXJ9LyR7aXRlbX1gLCBhc3luYyAoZXJyLCBzdGF0cykgPT4ge1xyXG4gICAgICAgIGlmIChlcnIpXHJcbiAgICAgICAgICB0aHJvdyBlcnJcclxuICAgICAgICBpZiAoc3RhdHMuaXNGaWxlKCkpIHtcclxuICAgICAgICAgIGZpbGVMaXN0VG90YWwucHVzaChzdGF0cy5zaXplKVxyXG4gICAgICAgICAgY2hlY2tFbmQoKVxyXG4gICAgICAgIH1cclxuICAgICAgICBlbHNlIGlmIChzdGF0cy5pc0RpcmVjdG9yeSgpKSB7XHJcbiAgICAgICAgICByZWN1cnNpdmVEaXJlY3RvcnkoYCR7Zm9sZGVyfS8ke2l0ZW19L2AsIGNoZWNrRW5kKVxyXG4gICAgICAgIH1cclxuICAgICAgfSlcclxuICAgIH0pXHJcbiAgICBmaWxlcy5sZW5ndGggPT09IDAgJiYgY2FsbGJhY2soKVxyXG4gIH0pXHJcbn1cclxuXHJcbmZ1bmN0aW9uIHN1bShhcnI6IG51bWJlcltdKSB7XHJcbiAgcmV0dXJuIGFyci5yZWR1Y2UoKHQ6IG51bWJlciwgYzogbnVtYmVyKSA9PiB7XHJcbiAgICByZXR1cm4gdCArIGNcclxuICB9LCAwKVxyXG59XHJcbmZ1bmN0aW9uIGZvcm1hdEJ5dGVzKGE6IG51bWJlciwgYj86IG51bWJlcik6IHN0cmluZyB7XHJcbiAgaWYgKGEgPT09IDApXHJcbiAgICByZXR1cm4gJzAgQnl0ZXMnXHJcbiAgY29uc3QgYyA9IDEwMjRcclxuICBjb25zdCBkID0gYiB8fCAyXHJcbiAgY29uc3QgZSA9IFsnQnl0ZXMnLCAnS0InLCAnTUInLCAnR0InLCAnVEInLCAnUEInLCAnRUInLCAnWkInLCAnWUInXVxyXG4gIGNvbnN0IGYgPSBNYXRoLmZsb29yKE1hdGgubG9nKGEpIC8gTWF0aC5sb2coYykpXHJcbiAgcmV0dXJuIGAke051bWJlci5wYXJzZUZsb2F0KChhIC8gYyAqKiBmKS50b0ZpeGVkKGQpKX0gJHtlW2ZdfWBcclxufVxyXG5cclxuZXhwb3J0IGZ1bmN0aW9uIHZpdGVCdWlsZEluZm8obmFtZTogc3RyaW5nKTogUGx1Z2luIHtcclxuICBsZXQgY29uZmlnOiBSZXNvbHZlZENvbmZpZ1xyXG4gIGxldCBzdGFydFRpbWU6IERheWpzXHJcbiAgbGV0IGVuZFRpbWU6IERheWpzXHJcbiAgcmV0dXJuIHtcclxuICAgIG5hbWU6ICd2aXRlOmJ1aWxkSW5mbycsXHJcbiAgICBjb25maWdSZXNvbHZlZChyZXNvbHZlZENvbmZpZykge1xyXG4gICAgICBjb25maWcgPSByZXNvbHZlZENvbmZpZ1xyXG4gICAgfSxcclxuICAgIGJ1aWxkU3RhcnQoKSB7XHJcbiAgICAgIGNvbnNvbGUubG9nKFxyXG4gICAgICAgIGJvbGQoXHJcbiAgICAgICAgICBncmVlbihcclxuICAgICAgICAgICAgYFx1RDgzRFx1REM0Rlx1NkIyMlx1OEZDRVx1NEY3Rlx1NzUyOCR7Ymx1ZShgWyR7bmFtZX1dYCl9XHVGRjBDXHU3M0IwXHU1NzI4XHU2QjYzXHU1MTY4XHU1MjlCXHU0RTNBXHU2MEE4JHtjb25maWcuY29tbWFuZCA9PT0gJ2J1aWxkJyA/ICdcdTYyNTNcdTUzMDUnIDogJ1x1N0YxNlx1OEJEMSdcclxuICAgICAgICAgICAgfWAsXHJcbiAgICAgICAgICApLFxyXG4gICAgICAgICksXHJcbiAgICAgIClcclxuICAgICAgaWYgKGNvbmZpZy5jb21tYW5kID09PSAnYnVpbGQnKVxyXG4gICAgICAgIHN0YXJ0VGltZSA9IGRheWpzKG5ldyBEYXRlKCkpXHJcbiAgICB9LFxyXG4gICAgY2xvc2VCdW5kbGUoKSB7XHJcbiAgICAgIGlmIChjb25maWcuY29tbWFuZCA9PT0gJ2J1aWxkJykge1xyXG4gICAgICAgIGVuZFRpbWUgPSBkYXlqcyhuZXcgRGF0ZSgpKVxyXG4gICAgICAgIHJlY3Vyc2l2ZURpcmVjdG9yeShjb25maWcuYnVpbGQub3V0RGlyLCAoKSA9PiB7XHJcbiAgICAgICAgICBjb25zb2xlLmxvZyhcclxuICAgICAgICAgICAgYm9sZChcclxuICAgICAgICAgICAgICBncmVlbihcclxuICAgICAgICAgICAgICAgIGBcdTYwNkRcdTU1OUNcdTYyNTNcdTUzMDVcdTVCOENcdTYyMTBcdUQ4M0NcdURGODlcdUZGMDhcdTYwM0JcdTc1MjhcdTY1RjYke2RheWpzXHJcbiAgICAgICAgICAgICAgICAgIC5kdXJhdGlvbihlbmRUaW1lLmRpZmYoc3RhcnRUaW1lKSlcclxuICAgICAgICAgICAgICAgICAgLmZvcm1hdCgnbW1cdTUyMDZzc1x1NzlEMicpfVx1RkYwQ1x1NjI1M1x1NTMwNVx1NTQwRVx1NzY4NFx1NTkyN1x1NUMwRlx1NEUzQSR7Zm9ybWF0Qnl0ZXMoXHJcbiAgICAgICAgICAgICAgICAgICAgc3VtKGZpbGVMaXN0VG90YWwpLFxyXG4gICAgICAgICAgICAgICAgICApfVx1RkYwOWAsXHJcbiAgICAgICAgICAgICAgKSxcclxuICAgICAgICAgICAgKSxcclxuICAgICAgICAgIClcclxuICAgICAgICB9KVxyXG4gICAgICB9XHJcbiAgICB9LFxyXG4gIH1cclxufVxyXG4iXSwKICAibWFwcGluZ3MiOiAiO0FBQ0EsU0FBUyxlQUFlO0FBQ3hCLFNBQVMscUJBQXFCO0FBQzlCLFlBQVksYUFBYTtBQUN6QixTQUFTLGVBQWU7OztBQ0h4QixPQUFPLFNBQVM7QUFDaEIsT0FBTyxZ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
