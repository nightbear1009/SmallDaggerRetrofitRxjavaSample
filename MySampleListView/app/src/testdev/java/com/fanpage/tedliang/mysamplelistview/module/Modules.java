package com.fanpage.tedliang.mysamplelistview.module;

public class Modules {

        public static Object[] list() {
            return new Object[] {
                    new MyModule(),
                    new testDevModule()
            };
        }

        private Modules() {
            // No instances.
        }

}